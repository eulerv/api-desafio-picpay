package com.github.eulerv.picpaydesafiobackend.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("TRANSACTIONS")
public record Transaction(
    @Id Long id,
    Long payer,
    Long payee,
    BigDecimal value,
    @CreatedDate LocalDateTime createdAt) { // Essa notação faz com que date seja gerado automaticamente quando transactions for injetada.
        public Transaction {
            value = value.setScale(2);
        }
}