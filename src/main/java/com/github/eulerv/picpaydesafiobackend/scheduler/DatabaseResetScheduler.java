package com.github.eulerv.picpaydesafiobackend.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
    
    private static final String URL = "https://api-desafio-picpay.onrender.com/wallets";
    private final RestTemplate restTemplate = new RestTemplate();

    // Agendamento RESET diário às 02:00
    @Scheduled(cron = "0 0 2 * * ?", zone = "America/Sao_Paulo")
    public void resetDatabaseDaily() {
        transactionRepository.deleteAll();
        walletRepository.deleteAll();
        userRepository.deleteAll();

        User defaultUser = new User("user", passwordEncoder.encode("123456"));
        userRepository.save(defaultUser);
        System.out.println("Banco de dados resetado com sucesso às 02:00.");
    }

    @Scheduled(fixedDelay = 25 * 60 * 1000) // To wake up application hosted on render
    public void sendPingToWakeUp() {
        try {
            restTemplate.getForObject(URL, String.class);
            System.out.println("Ping enviado com sucesso para " + URL);
        } catch (Exception e) {
            System.err.println("Erro ao enviar ping: " + e.getMessage());
        }
    }
}
