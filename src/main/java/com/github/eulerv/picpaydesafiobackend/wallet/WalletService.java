package com.github.eulerv.picpaydesafiobackend.wallet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;
    
    public Wallet create(Wallet wallet) {
        var newWallet = walletRepository.save(wallet);
        System.out.println("Criou carteira.");
        return newWallet;
    }
    
    public Wallet update(Wallet wallet, Long id) {
        var selectedWallet = walletRepository.findById(id).get();
        System.out.println("Atualizou carteira.");
        return walletRepository.save(selectedWallet);
    }

    public ResponseEntity<String> delete(Long id) {
        walletRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Wallet getWalletById(Long id) {
        return walletRepository.findById(id).get();
    }

    public List<Wallet> getWalletList() {
        return walletRepository.findAll();
    }
    public void print(String message) {
        System.out.println(message);
    }
}