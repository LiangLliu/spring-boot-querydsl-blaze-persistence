package com.lianglliu.springbootquerydsl.service;

import com.lianglliu.springbootquerydsl.models.QRole;
import com.lianglliu.springbootquerydsl.models.Role;
import com.lianglliu.springbootquerydsl.repository.RoleRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository repository;
    public Page<Role> pageRoles(String role, Pageable pageable) {
        Predicate predicate = QRole.role.name.contains(role);
        return repository.findAll(predicate, pageable);
    }

}
