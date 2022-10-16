package com.elec5619.hdhelper.service.impl;

import com.elec5619.hdhelper.dto.ResponseDto;
import com.elec5619.hdhelper.dto.student.SignInDto;
import com.elec5619.hdhelper.dto.student.SignInResponseDto;
import com.elec5619.hdhelper.dto.student.SignupDto;
import com.elec5619.hdhelper.entity.AuthenticationToken;
import com.elec5619.hdhelper.entity.Student;
import com.elec5619.hdhelper.exception.AuthenticationFailException;
import com.elec5619.hdhelper.exception.CustomException;
import com.elec5619.hdhelper.repository.StudentRepository;
import com.elec5619.hdhelper.service.AuthenticationService;
import com.elec5619.hdhelper.service.EmailService;
import com.elec5619.hdhelper.service.RegistrationService;
import com.elec5619.hdhelper.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private StudentService studentService;
    private StudentRepository studentRepository;
    private AuthenticationService authenticationService;
    private EmailService emailService;

    @Override
    @Transactional
    public ResponseDto signUp(SignupDto signupDto) {
        // check whether student exists
        if (Objects.nonNull(studentRepository.findByEmail(signupDto.getEmail()))) {
            throw new CustomException("User already exists!");
        }

        // hash the password
        String encryptedpassword = signupDto.getPassword();

        try {
            encryptedpassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Student student = new Student(signupDto.getFirstName(), signupDto.getLastName(),
                signupDto.getEmail(), encryptedpassword, signupDto.getGender());

        studentRepository.save(student);

        final AuthenticationToken authenticationToken = new AuthenticationToken(student);
        authenticationService.saveConfirmationToken(authenticationToken);

        String link = "http://localhost:8080/api/signup/confirm?token=" + authenticationToken.getToken();
        emailService.send(signupDto.getEmail(), buildEmail(signupDto.getFirstName(), link));

        ResponseDto responseDto = new ResponseDto("Success", "Student created successfully!");

        return responseDto;
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

    @Override
    @Transactional
    public String confirmToken(String token) {
        AuthenticationToken authenticationToken = authenticationService
                .getToken(token)
                .orElseThrow(() -> new IllegalStateException("Token not found!"));

        if (authenticationToken != null) {
            studentService.enableUser(authenticationToken.getStudent().getEmail());
        }

        return "Congratulations! You have activated your account!";
    }

    @Override
    public SignInResponseDto signIn(SignInDto signInDto) {
        Student student = studentRepository.findByEmail(signInDto.getEmail());

        if (Objects.isNull(student)) {
            throw new AuthenticationFailException("Student does not exist!");        }

        try {
            if (!student.getPassword().equals(hashPassword(signInDto.getPassword()))) {
                throw new AuthenticationFailException("Wrong password!");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (!student.getEnabled()) {
            throw new AuthenticationFailException("Account has not been activated!");
        }

        AuthenticationToken token = authenticationService.getToken(student);
        if (Objects.isNull(token)) {
            throw new CustomException("Token is not present!");
        }

        return new SignInResponseDto("Success", token.getToken(), student);

    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }
}
