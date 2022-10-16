package com.elec5619.hdhelper.service.impl;

import com.elec5619.hdhelper.entity.Tutor;
import com.elec5619.hdhelper.repository.TutorRepository;
import com.elec5619.hdhelper.service.TutorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TutorServiceImpl implements TutorService {

    private TutorRepository tutorRepository;

    @Override
    public Tutor saveTutor(Tutor tutor) {
        return tutorRepository.save(tutor);
    }
}
