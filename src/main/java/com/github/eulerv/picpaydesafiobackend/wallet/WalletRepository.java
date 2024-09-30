package com.github.eulerv.picpaydesafiobackend.wallet;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface WalletRepository extends ListCrudRepository<Wallet, Long> {

    List<Wallet> findByUserId(Long userId);

    Optional<Wallet> findByIdAndUserId(Long id, Long userId);

    @Modifying
    @Query("DELETE FROM wallets WHERE id = :id AND user_id = :userId")
    void deleteByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);

    @Modifying
    @Query("DELETE FROM wallets WHERE user_id = :userId")
    void deleteAllByUserId(@Param("userId") Long userId);
}
