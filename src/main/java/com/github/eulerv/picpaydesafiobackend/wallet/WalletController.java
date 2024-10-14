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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "wallets")
@Tag(name = "Carteiras", description = "Operações relacionadas às carteiras")
@SecurityRequirement(name = "bearerAuth")
public class WalletController {
    private static final Logger LOG = LoggerFactory.getLogger(WalletController.class);
    @Autowired
    private WalletService walletService;
// Os campos operation e o apiresponses são para o swagger, documentação da API lá no index.html
    @Operation(summary = "Lista todas as carteiras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de carteiras ok!"),
            @ApiResponse(responseCode = "401", description = "Não autorizado!") })
    @GetMapping
    public ResponseEntity<List<Wallet>> getWallets() {
        List<Wallet> wallets = walletService.getWalletList();
        return ResponseEntity.ok(wallets);
    }

    @Operation(summary = "Obtém uma carteira pelo seuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carteira obtida!o"),
            @ApiResponse(responseCode = "404", description = "Carteira não encontrada!"),
            @ApiResponse(responseCode = "401", description = "Não autorizado!") })
    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable Long id) {
        Wallet wallet = walletService.getWalletById(id);
        return ResponseEntity.ok(wallet);
    }

    @Operation(summary = "Cria nova carteira")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Carteira criada!"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos!"),
        @ApiResponse(responseCode = "401", description = "Não autorizado!")
    })
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
                                "Obs: 'Userid' é o seu id, de quem está interagindo com a API. Foi a forma de multi-tenancy utilizada.");
    }

    @Operation(summary = "Atualiza uma carteira existente pelo seu id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Carteira atualizada!"),
        @ApiResponse(responseCode = "404", description = "Carteira não encontrada!"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos!"),
        @ApiResponse(responseCode = "401", description = "Não autorizado!")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> updateWallet(@RequestBody Wallet wallet, @PathVariable Long id) {
        walletService.update(wallet, id);
        return ResponseEntity.ok(
                "Carteira atualizada com sucesso." +
                        "id = " + id + " - Nome: " + wallet.fullName() +
                        " - CPF: " + wallet.cpf() +
                        " - Email: " + wallet.email() +
                        " - Senha: " + wallet.password() +
                        " - Tipo: " + wallet.type() +
                        " - Saldo: " + wallet.balance());
    }
    @Operation(summary = "Exclui uma carteira pelo sei id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Carteira excluída!"),
        @ApiResponse(responseCode = "404", description = "Carteira não encontrada!"),
        @ApiResponse(responseCode = "401", description = "Não autorizado!")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWallet(@PathVariable Long id) {
        walletService.delete(id);
        return ResponseEntity.ok("Carteira " + id + " excluída com sucesso.");
    }
    @Operation(summary = "Exclui todas as carteiras!")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Todas as carteiras foram excluídas!"),
        @ApiResponse(responseCode = "401", description = "Não autorizado!")
    })    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllWallets() {
        walletService.deleteAll();
        return ResponseEntity.ok("Todas as carteiras foram excluídas!");
    }
}