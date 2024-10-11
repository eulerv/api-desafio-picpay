package com.github.eulerv.picpaydesafiobackend.authenticationPackage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.eulerv.picpaydesafiobackend.authenticationPackage.dto.CredentialRequest;
import com.github.eulerv.picpaydesafiobackend.authenticationPackage.service.JwtTokenUtil;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public String authenticate(@RequestBody CredentialRequest credentialRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentialRequest.username(), credentialRequest.password()));
        String token = jwtTokenUtil.generateToken(credentialRequest.username());
        return token;
    }
}
