package com.github.eulerv.picpaydesafiobackend.cleanDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.eulerv.picpaydesafiobackend.transaction.TransactionService;
import com.github.eulerv.picpaydesafiobackend.wallet.WalletService;

@RestController
@RequestMapping("/cleanDB")
public class CleanDB {
  @Autowired
  private WalletService walletService;
  @Autowired
  private TransactionService transactionService;
  @DeleteMapping
  public ResponseEntity<String> doCleanDB() {
    transactionService.deleteAll();
    walletService.deleteAll();
    return ResponseEntity.ok("Todas as carteiras e transações foram excluídas com sucesso!");
}
}
