package com.github.eulerv.picpaydesafiobackend.authenticationPackage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.eulerv.picpaydesafiobackend.authenticationPackage.dto.CredentialRequest;
import com.github.eulerv.picpaydesafiobackend.authenticationPackage.service.UserService;

@RestController
@RequestMapping("/signup")
@CrossOrigin(allowCredentials =  "*"  , methods = { RequestMethod.POST }  , maxAge = 3600)
public class SignupController {
    @Autowired
    private UserService userService;
    // @Autowired
    // private EmailService emailService;
    @PostMapping
    public ResponseEntity<String> signup(@RequestBody CredentialRequest credentialRequest) {
        userService.registerUser(credentialRequest.username(), credentialRequest.password());
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso!");
    }
}
