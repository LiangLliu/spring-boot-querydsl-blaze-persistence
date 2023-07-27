package com.lianglliu.springbootquerydsl.service;

import com.lianglliu.springbootquerydsl.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;
}
