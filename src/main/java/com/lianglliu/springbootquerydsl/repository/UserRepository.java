package com.lianglliu.springbootquerydsl.repository;

import com.lianglliu.springbootquerydsl.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
