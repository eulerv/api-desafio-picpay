package com.github.eulerv.picpaydesafiobackend.transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends ListCrudRepository<Transaction, Long> {
        List<Transaction> findByUserId(Long userId);

    Optional<Transaction> findByIdAndUserId(Long id, Long userId);

    @Modifying
    @Query("DELETE FROM transactions WHERE id = :id AND user_id = :userId")
    void deleteByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);

    @Modifying
    @Query("DELETE FROM transactions WHERE user_id = :userId")
    void deleteAllByUserId(@Param("userId") Long userId);
}