package com.elec5619.hdhelper.controller;

import com.elec5619.hdhelper.entity.Lecturer;
import com.elec5619.hdhelper.repository.LecturerRepository;
import com.elec5619.hdhelper.service.LecturerService;
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
@WebMvcTest(LecturerController.class)
class LecturerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LecturerRepository lecturerRepository;

    @Test
    void saveLecturer() {
        Lecturer lecturer = new Lecturer();
        lecturer.setFirstName("Hao");
        lecturer.setLastName("Chen");
        lecturerRepository.save(lecturer);
    }
}