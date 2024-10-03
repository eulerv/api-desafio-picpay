package com.github.eulerv.picpaydesafiobackend.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.eulerv.picpaydesafiobackend.authenticationPackage.model.User;
import com.github.eulerv.picpaydesafiobackend.authenticationPackage.service.UserService;
import com.github.eulerv.picpaydesafiobackend.authorization.AuthorizerService;
import com.github.eulerv.picpaydesafiobackend.exception.InvalidTransactionException;
import com.github.eulerv.picpaydesafiobackend.wallet.Wallet;
import com.github.eulerv.picpaydesafiobackend.wallet.WalletRepository;
import com.github.eulerv.picpaydesafiobackend.wallet.WalletType;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private AuthorizerService authorizerService;
    @Autowired
    private UserService userService;

    private Long getCurrentUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return user.getId();
    }

    @Transactional
    public Transaction create(Transaction transaction) {
        Long userId = getCurrentUserId();
        transaction.setUserId(userId);
        validate(transaction);
        transactionRepository.save(transaction);
        var walletPayer = walletRepository.findById(transaction.getPayer()).get();
        var walletPayee = walletRepository.findById(transaction.getPayee()).get();
        walletRepository.save(walletPayer.debit(transaction.getValue()));
        walletRepository.save(walletPayee.credit(transaction.getValue()));
        // 4 - Chamar serviços validadores externos
        authorizerService.authorize(transaction);
        return transaction;
    }

    /*- the payer must have a common wallet - the payer must have enough balance - the payer must not be the payee -*/
    private void validate(Transaction transaction) {
        walletRepository.findById(transaction.getPayee())
                .map(payee -> walletRepository.findById(transaction.getPayer())
                        .map(payer -> isTransactionValid(transaction,payer) ? transaction : null)
                        .orElseThrow(() -> new InvalidTransactionException(
                                "Invalid Transaction - %s".formatted(transaction))))
                .orElseThrow(() -> new InvalidTransactionException(
                        "Invalid Transaction (Payee not found) - %s".formatted(transaction)));
    }
    private boolean isTransactionValid(Transaction transaction,Wallet payer){
        return payer.type() == WalletType.COMMON.getValue() &&
        payer.balance().compareTo(transaction.getValue()) >= 0 &&
        !payer.id().equals(transaction.getPayee());
    }

    public List<Transaction> list() {
        Long userId = getCurrentUserId();
        return transactionRepository.findByUserId(userId);
    }
}