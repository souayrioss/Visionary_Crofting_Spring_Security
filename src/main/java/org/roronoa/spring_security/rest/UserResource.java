package org.roronoa.spring_security.rest;


import lombok.RequiredArgsConstructor;
import org.roronoa.spring_security.config.JwtUtils;
import org.roronoa.spring_security.dto.AuthDTO;
import org.roronoa.spring_security.dto.ResponseDTO;
import org.roronoa.spring_security.dto.UserDTO;
import org.roronoa.spring_security.entity.UserApp;
import org.roronoa.spring_security.service.IUserService;
import org.roronoa.spring_security.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.roronoa.spring_security.util.IConstantes.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserResource {

    private final IUserService userService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping(path = "/register")
    public ResponseEntity<ResponseDTO<UserDTO>> register(@RequestBody @Valid UserDTO userDTO){
        try {
            UserApp user = EntityUtils.userDTOToUser(userDTO);
            UserApp userApp = userService.save(user);
            ResponseDTO<UserDTO> response = new ResponseDTO<>() ;
            response.setData(EntityUtils.userToUserDTO(userApp));
            response.setStatus(CODE_001);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ResponseDTO<UserDTO> response = new ResponseDTO<>() ;
            response.setStatus(CODE_000);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
    @PostMapping(path = "/login")
    public ResponseEntity<ResponseDTO<UserDetails>> login(@RequestBody AuthDTO authDTO){
        try {

            UserDetails user = userService.findByEmail(authDTO.getEmail());
            ResponseDTO<UserDetails> response = new ResponseDTO<>() ;
            if (user != null){
                response.setMessage(jwtUtils.generateToken(user));
                   response.setStatus(CODE_001);
                   response.setData(user);
            }else {
                response.setMessage("user not found");
                response.setStatus(CODE_002);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ResponseDTO<UserDetails> response = new ResponseDTO<>() ;
            response.setStatus(CODE_000);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/user/{uuid}")
    public ResponseEntity<ResponseDTO<UserDTO>> getUser(@PathVariable @NotEmpty @NotBlank String uuid){
        try {
            ResponseDTO<UserDTO> response = new ResponseDTO<>() ;
            UserApp user = userService.getUser(uuid);
            if (!Objects.isNull(user)){
                response.setData(EntityUtils.userToUserDTO(user));
                response.setStatus(CODE_001);
            }else {
                response.setStatus("user not exist");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ResponseDTO<UserDTO> response = new ResponseDTO<>() ;
            response.setStatus(CODE_000);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
    @GetMapping(path = "/users")
    public ResponseEntity<ResponseDTO<List<UserDTO>>> getListUser(){
        try {
           List<UserApp> users = userService.getListUsers();
            ResponseDTO<List<UserDTO>> response = new ResponseDTO<>() ;
            List<UserDTO> usersDTO = users.stream().map(EntityUtils::userToUserDTO).collect(Collectors.toList());
            response.setStatus(CODE_001);
            response.setData(usersDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ResponseDTO<List<UserDTO>> response = new ResponseDTO<>() ;
            response.setStatus(CODE_000);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

}
