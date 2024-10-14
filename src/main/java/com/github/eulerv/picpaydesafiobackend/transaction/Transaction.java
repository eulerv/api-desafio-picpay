package com.github.eulerv.picpaydesafiobackend.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Representa uma transação entre carteiras")
@Table("transactions")
public class Transaction {
    @Id
    @Schema(description = "Identificador único da transação", example = "1")
    private Long id;
    @Schema(description = "ID do usuário que realizou a transação", example = "1")
    private Long userId;
    @NotNull
    @Schema(description = "ID da carteira pagadora", example = "1")
    private Long payer;
    @NotNull
    @Schema(description = "ID da carteira recebedora", example = "2")
    private Long payee;
    @NotNull
    @DecimalMin("0.01")
    @Schema(description = "Valor da transação", example = "100.00")
    private BigDecimal value;
    @Schema(description = "Data e hora da transação", example = "2023-10-14T12:34:56")
    @CreatedDate // Essa notação vai fazer com que date seja gerado automaticamente quando transactions
                 // for injetada, com base no horário do servidor.
    private LocalDateTime createdAt;

    public Transaction(Long id, Long userId, Long payer, Long payee, BigDecimal value, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.payer = payer;
        this.payee = payee;
        this.value = value.setScale(2);
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPayer() {
        return payer;
    }

    public void setPayer(Long payer) {
        this.payer = payer;
    }

    public Long getPayee() {
        return payee;
    }

    public void setPayee(Long payee) {
        this.payee = payee;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value.setScale(2);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
