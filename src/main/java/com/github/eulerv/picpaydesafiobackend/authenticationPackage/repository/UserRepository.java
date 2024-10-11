package com.github.eulerv.picpaydesafiobackend.authenticationPackage.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.github.eulerv.picpaydesafiobackend.authenticationPackage.model.User;

public interface UserRepository extends ListCrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}