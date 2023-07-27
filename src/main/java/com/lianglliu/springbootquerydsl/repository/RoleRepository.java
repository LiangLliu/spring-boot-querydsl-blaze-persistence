package com.lianglliu.springbootquerydsl.repository;

import com.lianglliu.springbootquerydsl.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>, QuerydslPredicateExecutor<Role> {
}
