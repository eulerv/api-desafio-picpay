package com.github.eulerv.picpaydesafiobackend.authenticationPackage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.eulerv.picpaydesafiobackend.authenticationPackage.dto.CredentialRequest;
import com.github.eulerv.picpaydesafiobackend.authenticationPackage.service.UserService;

@RestController
@RequestMapping("/signup")
public class SignupController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> signup(@RequestBody CredentialRequest credentialRequest) {
        userService.registerUser(credentialRequest.username(), credentialRequest.password());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuário registrado com sucesso! Prossiga com o login em \"/authenticate\".);");
    }
}
