package com.elec5619.hdhelper.service;

import com.elec5619.hdhelper.dto.ResponseDto;
import com.elec5619.hdhelper.dto.student.SignInDto;
import com.elec5619.hdhelper.dto.student.SignInResponseDto;
import com.elec5619.hdhelper.dto.student.SignupDto;

public interface RegistrationService {

    ResponseDto signUp(SignupDto signupDto);
    SignInResponseDto signIn(SignInDto signInDto);
    String confirmToken(String token);
}
