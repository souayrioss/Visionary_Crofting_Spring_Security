package org.roronoa.spring_security.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.roronoa.spring_security.entity.Role;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class UserDTO {
    private String uuid;
    @NotBlank
    @NotEmpty
    private String name;
    @NotBlank
    @NotEmpty
    @Email(message = "email not valid")
    private String email;
    @NotBlank
    @NotEmpty
    private String password;
    //@NotBlank
    @NotEmpty
    private String phone;


    private Role role;

}
