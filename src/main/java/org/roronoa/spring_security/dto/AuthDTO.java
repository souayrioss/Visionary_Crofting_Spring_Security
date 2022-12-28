package org.roronoa.spring_security.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class AuthDTO {
    @NotBlank
    @NotEmpty
    @Email(message = "email not valid")
    private String email;
    @NotBlank
    @NotEmpty
    private String password;
}
