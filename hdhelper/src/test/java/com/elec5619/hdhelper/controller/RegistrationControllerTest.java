package com.elec5619.hdhelper.controller;

import com.elec5619.hdhelper.dto.student.SignInDto;
import com.elec5619.hdhelper.dto.student.SignupDto;
import com.elec5619.hdhelper.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RegistrationService registrationService;

    @Test
    void signUp() {
        SignupDto signupDto = new SignupDto("Hao", "Chen",
                "M", "hao@mail.com", "123");

        registrationService.signUp(signupDto);
    }

    @Test
    void confirm() {
        String token = "abc";
        registrationService.confirmToken(token);
    }

    @Test
    void singIn() {
        SignInDto signInDto = new SignInDto("hao@mail.com", "123");
        registrationService.signIn(signInDto);
    }
}