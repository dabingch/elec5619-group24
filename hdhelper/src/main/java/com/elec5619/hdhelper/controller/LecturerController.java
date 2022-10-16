package com.elec5619.hdhelper.controller;

import com.elec5619.hdhelper.entity.Lecturer;
import com.elec5619.hdhelper.repository.LecturerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lecturers")
@AllArgsConstructor
public class LecturerController {

    private LecturerRepository lecturerRepository;

    // Create a lecturer
    @PostMapping
    public ResponseEntity<Lecturer> saveLecturer(@RequestBody Lecturer lecturer) {
        return new ResponseEntity<>(lecturerRepository.save(lecturer), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Lecturer>> findLecturerByFirstNameAndLastName(@RequestParam(value = "firstName") String firstName,
                                                                             @RequestParam(value = "lastName") String lastName) {
        return new ResponseEntity<>(lecturerRepository.findByFirstNameAndLastName(firstName, lastName), HttpStatus.OK);
    }
}
