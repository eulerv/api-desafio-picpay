package com.github.eulerv.picpaydesafiobackend.wallet;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.github.eulerv.picpaydesafiobackend.authenticationPackage.model.User;
import com.github.eulerv.picpaydesafiobackend.authenticationPackage.service.UserService;
import com.github.eulerv.picpaydesafiobackend.exception.ResourceNotFoundException;

@Service
public class WalletService {
    private static final Logger LOG = LoggerFactory.getLogger(WalletController.class);
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserService userService;

    private Long getCurrentUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return user.getId();
    }

    public Wallet create(Wallet wallet) {
        Long userId = getCurrentUserId();
        Wallet newWallet = new Wallet(null, userId, wallet.fullName(), wallet.cpf(), wallet.email(), wallet.password(),
                wallet.type(), wallet.balance());
        return walletRepository.save(newWallet);
    }

    public Wallet getWalletById(Long id) {
        Long userId = getCurrentUserId();
        return walletRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Carteira não encontrada"));
    }

    public List<Wallet> getWalletList() {
        Long userId = getCurrentUserId();
        return walletRepository.findByUserId(userId);
    }

    public Wallet update(Wallet wallet, Long id) {
        Long userId = getCurrentUserId();

        Wallet existingWallet = walletRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Carteira não encontrada"));
        Wallet updatedWallet = new Wallet(
                existingWallet.id(),
                userId,
                wallet.fullName(),
                wallet.cpf(),
                wallet.email(),
                wallet.password(),
                wallet.type(),
                wallet.balance());
        return walletRepository.save(updatedWallet);
    }

    public void delete(Long id) {
        Long userId = getCurrentUserId();
        LOG.info("Método delete() chamado no Service. Id:" + id + " - userId:" + userId);
        walletRepository.deleteByIdAndUserId(id, userId);
    }

    public void deleteAll() {
        Long userId = getCurrentUserId();
        walletRepository.deleteAllByUserId(userId);
    }

    public void print(String message) {
        System.out.println(message);
    }
}