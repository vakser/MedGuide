package org.example.medguide.service;

import lombok.RequiredArgsConstructor;
import org.example.medguide.model.Role;
import org.example.medguide.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

}
