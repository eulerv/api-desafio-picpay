package com.github.eulerv.picpaydesafiobackend.transaction;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "transactions")
@Tag(name = "Transações", description = "Operações relacionadas às transações")
@SecurityRequirement(name = "bearerAuth")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(summary = "Cria uma nova transação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Transação criada!"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos!"),
        @ApiResponse(responseCode = "401", description = "Não autorizado!")
    })
    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.create(transaction);
    }

    @Operation(summary = "Lista todas as transações")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de transações obtida com sucesso!"),
        @ApiResponse(responseCode = "401", description = "Não autorizado!")
    })
    @GetMapping
        public List<Transaction> list() {
        return transactionService.list();
    }
}