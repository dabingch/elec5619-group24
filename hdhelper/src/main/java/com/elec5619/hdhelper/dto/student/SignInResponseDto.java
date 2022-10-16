package com.elec5619.hdhelper.dto.student;

import com.elec5619.hdhelper.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInResponseDto {

    private String status;
    private String token;
    private Student student;
}
