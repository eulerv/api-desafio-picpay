package com.github.eulerv.picpaydesafiobackend.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("transactions")
public class Transaction {

    @Id
    private Long id;
    private Long userId;
    private Long payer;
    private Long payee;
    private BigDecimal value;
    
    @CreatedDate
    private LocalDateTime createdAt; // Essa notação faz com que date seja gerado automaticamente quando transactions for injetada.

    // Construtor
    public Transaction(Long id, Long userId, Long payer, Long payee, BigDecimal value, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.payer = payer;
        this.payee = payee;
        this.value = value.setScale(2);
        this.createdAt = createdAt;
    }

    // Getters e Setters
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

    // O setter de createdAt pode ser omitido se a data for gerada automaticamente
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
