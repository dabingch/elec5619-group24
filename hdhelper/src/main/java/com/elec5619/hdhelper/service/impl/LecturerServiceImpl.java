package com.elec5619.hdhelper.service.impl;

import com.elec5619.hdhelper.entity.Lecturer;
import com.elec5619.hdhelper.repository.LecturerRepository;
import com.elec5619.hdhelper.service.LecturerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LecturerServiceImpl implements LecturerService {

    private LecturerRepository lecturerRepository;

    @Override
    public Lecturer saveLecturer(Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }
}
