package com.lianglliu.springbootquerydsl.repository;

import com.lianglliu.springbootquerydsl.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
