package com.github.eulerv.picpaydesafiobackend.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.eulerv.picpaydesafiobackend.authenticationPackage.model.User;
import com.github.eulerv.picpaydesafiobackend.authenticationPackage.repository.UserRepository;
import com.github.eulerv.picpaydesafiobackend.transaction.TransactionRepository;
import com.github.eulerv.picpaydesafiobackend.wallet.WalletRepository;

@Component
public class DatabaseResetScheduler {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Agendamento RESET diário às 02:00
    @Scheduled(cron = "0 0 2 * * ?", zone = "America/Sao_Paulo")
    public void resetDatabase() {
        transactionRepository.deleteAll();
        walletRepository.deleteAll();
        userRepository.deleteAll();

        User defaultUser = new User("user", passwordEncoder.encode("123456"));
        userRepository.save(defaultUser);
        System.out.println("Banco de dados resetado com sucesso às 02:00.");
    }
}
