package com.elec5619.hdhelper.controller;

import com.elec5619.hdhelper.entity.Tutor;
import com.elec5619.hdhelper.repository.TutorRepository;
import com.elec5619.hdhelper.service.TutorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutors")
@AllArgsConstructor
public class TutorController {

    private TutorService tutorService;

    @Autowired
    private TutorRepository tutorRepository;

    // Create a tutor
    @PostMapping
    public ResponseEntity<Tutor> saveTutor(@RequestBody Tutor tutor) {
        return new ResponseEntity<>(tutorService.saveTutor(tutor), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tutor>> findLecturerByFirstNameAndLastName(@RequestParam(value = "firstName") String firstName,
                                                                             @RequestParam(value = "lastName") String lastName) {
        return new ResponseEntity<>(tutorRepository.findByFirstNameAndLastName(firstName, lastName), HttpStatus.OK);
    }
}
