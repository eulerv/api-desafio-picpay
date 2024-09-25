package com.github.eulerv.picpaydesafiobackend.wallet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    // private static final Logger LOG = LoggerFactory.getLogger(WalletController.class);
    @Autowired
    private WalletService walletService;

    @GetMapping("")
    public List<Wallet> getWallets() {
        return walletService.getWalletList();
    }

    @GetMapping("/{id}")
    public Wallet getWalletById(@PathVariable Long id) {
        return walletService.getWalletById(id);
    }
    @PostMapping
    public Wallet createWallet(@RequestBody Wallet wallet) {
        return walletService.create(wallet);
    }
    @PutMapping("/{id}")
    public Wallet updateWallet(@RequestBody Wallet wallet, @PathVariable Long id) {
        return walletService.update(wallet, id);
    }
}