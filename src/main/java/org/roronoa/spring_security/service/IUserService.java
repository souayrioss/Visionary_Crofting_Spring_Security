package org.roronoa.spring_security.service;

import org.roronoa.spring_security.entity.UserApp;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUserService {
    UserApp save(UserApp user);
    UserApp getUser(String uuid);
    List<UserApp> getListUsers();

    UserDetails findByEmail(String email);

}
