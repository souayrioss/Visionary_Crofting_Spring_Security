package org.roronoa.spring_security.repository;

import org.roronoa.spring_security.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserApp,Long> {
    Optional<UserApp> findByUuid(String uuid);
    UserApp findByEmail(String email);
}
