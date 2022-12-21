package org.roronoa.spring_security.service.Imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.roronoa.spring_security.entity.User;
import org.roronoa.spring_security.repository.UserRepository;
import org.roronoa.spring_security.service.IUserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImp implements IUserService {

    private final UserRepository userRepository;
    @Override
    public User save(@Valid User user) {
        user.setUuid(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public User getUser(String uuid) {
        Optional<User> user = userRepository.findByUuid(uuid);
        if (user.isPresent()) return user.get();
        else return null;
    }

    @Override
    public List<User> getListUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }
}
