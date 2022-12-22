package org.roronoa.spring_security.service.Imp;

import org.roronoa.spring_security.entity.Role;
import org.roronoa.spring_security.repository.RoleRepository;
import org.roronoa.spring_security.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImp implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role getRole(Long id_role) {
        Optional<Role> role = roleRepository.findById(id_role);
        if (role.isPresent()){
            return role.get();
        }
        return null;
    }
}
