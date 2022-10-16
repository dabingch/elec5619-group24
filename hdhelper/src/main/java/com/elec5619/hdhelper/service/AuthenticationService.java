package com.elec5619.hdhelper.service;

import com.elec5619.hdhelper.entity.AuthenticationToken;
import com.elec5619.hdhelper.entity.Student;

import java.util.Optional;

public interface AuthenticationService {
    void saveConfirmationToken(AuthenticationToken authenticationToken);
    AuthenticationToken getToken(Student student);
    Optional<AuthenticationToken> getToken(String token);
}
