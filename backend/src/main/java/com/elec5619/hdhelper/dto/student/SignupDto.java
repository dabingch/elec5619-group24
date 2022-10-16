package com.elec5619.hdhelper.dto.student;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupDto {

    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String password;
}
