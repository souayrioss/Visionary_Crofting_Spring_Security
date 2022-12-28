package org.roronoa.spring_security.service.Imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.roronoa.spring_security.entity.Role;
import org.roronoa.spring_security.entity.UserApp;
import org.roronoa.spring_security.repository.UserRepository;
import org.roronoa.spring_security.service.IRoleService;
import org.roronoa.spring_security.service.IUserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.validation.Valid;
import java.util.*;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImp implements IUserService {

    private  final IRoleService roleService;
    private final UserRepository userRepository;
    @Override
    public UserApp save(@Valid UserApp user) {
        user.setUuid(UUID.randomUUID().toString());
        Role role = roleService.getRole(3L);
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public UserApp getUser(String uuid) {
        Optional<UserApp> user = userRepository.findByUuid(uuid);
        return user.orElse(null);
    }

    @Override
    public List<UserApp> getListUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails findByEmail(String email){
        UserApp userApp = userRepository.findByEmail(email);
        if (Objects.isNull(userApp)) {
            return null;
        }
        return new User(userApp.getEmail(),userApp.getPassword(), Collections.singleton(new SimpleGrantedAuthority(userApp.getRole().getNom())));
    }
}
