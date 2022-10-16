package com.elec5619.hdhelper.controller;

import com.elec5619.hdhelper.dto.ResponseDto;
import com.elec5619.hdhelper.dto.student.SignInDto;
import com.elec5619.hdhelper.dto.student.SignInResponseDto;
import com.elec5619.hdhelper.dto.student.SignupDto;
import com.elec5619.hdhelper.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    // Sign up
    @PostMapping("/signup")
    public ResponseDto signUp(@RequestBody SignupDto signupDto) {
        return registrationService.signUp(signupDto);
    }

    // Confirm email of a student
    @GetMapping(path = "/signup/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    // Sign in
    @PostMapping("/signin")
    public SignInResponseDto singIn(@RequestBody SignInDto signInDto) {
        return registrationService.signIn(signInDto);
    }
}
