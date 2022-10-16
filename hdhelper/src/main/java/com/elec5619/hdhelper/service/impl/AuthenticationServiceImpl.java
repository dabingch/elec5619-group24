package com.elec5619.hdhelper.service.impl;

import com.elec5619.hdhelper.entity.AuthenticationToken;
import com.elec5619.hdhelper.entity.Student;
import com.elec5619.hdhelper.repository.TokenRepository;
import com.elec5619.hdhelper.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    TokenRepository tokenRepository;
    @Override
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    @Override
    public AuthenticationToken getToken(Student student) {
        return tokenRepository.findByStudent(student);
    }

    @Override
    public Optional<AuthenticationToken> getToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
