package org.roronoa.spring_security.service;

import org.roronoa.spring_security.entity.User;
import org.roronoa.spring_security.dto.UserDTO;

import java.util.List;

public interface IUserService {
    User save(User user);
    User getUser(String uuid);
    List<User> getListUsers();

}
