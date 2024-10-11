package com.github.eulerv.picpaydesafiobackend.wallet;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "wallets")
public class WalletController {
    private static final Logger LOG = LoggerFactory.getLogger(WalletController.class);
    @Autowired
    private WalletService walletService;
   @GetMapping
    public ResponseEntity<List<Wallet>> getWallets() {
        List<Wallet> wallets = walletService.getWalletList();
        return ResponseEntity.ok(wallets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable Long id) {
        Wallet wallet = walletService.getWalletById(id);
        return ResponseEntity.ok(wallet);
    }

    @PostMapping
    public ResponseEntity<String> createWallet(@RequestBody Wallet wallet) {
        walletService.create(wallet);
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            "Carteira criada com sucesso." +
            "Nome: " + wallet.fullName() +
            ", CPF: " + wallet.cpf() +
            ", Email: " + wallet.email() +
            ", Senha: " + wallet.password() +
            ", Tipo: " + wallet.type() +
            ", Saldo: " + wallet.balance() +
            ". Vá até a tela de GET para obter o id que foi instanciado. " +
            "Obs: 'Userid' é o seu id, de quem está interagindo com a API. Foi a forma de multi-tenancy utilizada."
            );
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateWallet(@RequestBody Wallet wallet, @PathVariable Long id) {
        walletService.update(wallet, id);
        return ResponseEntity.ok(
            "Carteira atualizada com sucesso." +
            "id = " + id +  " - Nome: " + wallet.fullName() +
            " - CPF: " + wallet.cpf() +
            " - Email: " + wallet.email() +
            " - Senha: " + wallet.password() +
            " - Tipo: " + wallet.type() +
            " - Saldo: " + wallet.balance()
            );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWallet(@PathVariable Long id) {
        walletService.delete(id);
        return ResponseEntity.ok("Carteira " + id + " excluída com sucesso.");
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllWallets() {
        walletService.deleteAll();
        return ResponseEntity.ok("Todas as carteiras foram excluídas com sucesso.");
    }
}