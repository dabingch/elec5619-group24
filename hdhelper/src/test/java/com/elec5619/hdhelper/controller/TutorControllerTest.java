package com.elec5619.hdhelper.controller;

import com.elec5619.hdhelper.entity.Tutor;
import com.elec5619.hdhelper.repository.TutorRepository;
import com.elec5619.hdhelper.service.TutorService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TutorController.class)
class TutorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TutorService tutorService;

    @MockBean
    private TutorRepository tutorRepository;

    @Test
    void saveTutor() {
        Tutor tutor = new Tutor();
        tutor.setFirstName("Hao");
        tutor.setLastName("Chen");

        tutorService.saveTutor(tutor);
    }
}