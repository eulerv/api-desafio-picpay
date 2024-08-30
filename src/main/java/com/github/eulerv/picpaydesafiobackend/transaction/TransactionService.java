package com.github.eulerv.picpaydesafiobackend.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.eulerv.picpaydesafiobackend.authorization.AuthorizerService;
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

    @Transactional
    public Transaction create(Transaction transaction) {
        // 1 - Validar
        validate(transaction);

        // 2 - Criar transação
        var newTransaction = transactionRepository.save(transaction);

        // 3 - Creditar e debitar das carteiras com segurança
        var walletPayer = walletRepository.findById(transaction.payer()).get();
        var walletPayee = walletRepository.findById(transaction.payee()).get();
        walletRepository.save(walletPayer.debit(transaction.value()));
        walletRepository.save(walletPayee.credit(transaction.value()));

        // 4 - Chamar serviços validadores externos
        authorizerService.authorize(transaction);

        return newTransaction;
    }

    /**
     * - the payer must have a common wallet
     * - the payer must have enough balance
     * - the payer must not be the payee
     */
    private void validate(Transaction transaction) {
        walletRepository.findById(transaction.payee())
                .map(payee -> walletRepository.findById(transaction.payer())
                        .map(payer -> isTransactionValid(transaction,payer) ? transaction : null)
                        .orElseThrow(() -> new InvalidTransactionException(
                                "Invalid Transaction - %s".formatted(transaction))))
                .orElseThrow(() -> new InvalidTransactionException(
                        "Invalid Transaction (Payee not found) - %s".formatted(transaction)));
    }
    private boolean isTransactionValid(Transaction transaction,Wallet payer){
        return payer.type() == WalletType.COMMON.getValue() &&
        payer.balance().compareTo(transaction.value()) >= 0 &&
        !payer.id().equals(transaction.payee());
    }

    public List<Transaction> list() {
        return transactionRepository.findAll();
    }
}