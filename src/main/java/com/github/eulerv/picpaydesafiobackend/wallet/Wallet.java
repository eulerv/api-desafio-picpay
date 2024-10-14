package com.github.eulerv.picpaydesafiobackend.wallet;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Representa uma carteira de usuário")
@Table("wallets")
public record Wallet(
    @Id @Schema(description = "Identificador único da carteira", example = "1")
    Long id,
    @Schema(description = "ID do usuário proprietário da carteira", example = "1")
    Long userId,
    @NotBlank @Schema(description = "Nome completo do usuário", example = "João da Silva")
    String fullName,
    @NotNull @Schema(description = "CPF do usuário(11 dígitos)", example = "12345678901")
    Long cpf,
    @Email @Schema(description = "Endereço de e-mail do usuário", example = "joao@example.com")
    String email,
    @NotBlank @Schema(description = "Senha de acesso", example = "123456")
    String password,
    @Schema(description = "Tipo da carteira (1 = Comum, 2 = Lojista)", example = "1")
    int type,
    @Schema(description = "Saldo disponível", example = "10000.00")
    BigDecimal balance) {

    public Wallet debit(BigDecimal value) {
        return new Wallet(id, userId, fullName, cpf, email, password, type, balance.subtract(value));
    }

    public Wallet credit(BigDecimal value) {
        return new Wallet(id, userId, fullName, cpf, email, password, type, balance.add(value));
    }
}