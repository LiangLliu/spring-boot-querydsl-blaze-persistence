package com.lianglliu.springbootquerydsl.service;

import com.lianglliu.springbootquerydsl.models.QUser;
import com.lianglliu.springbootquerydsl.models.QUserRole;
import com.lianglliu.springbootquerydsl.models.User;
import com.lianglliu.springbootquerydsl.repository.UserRepository;
import com.lianglliu.springbootquerydsl.repository.dsl.UserDslRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDslRepository userDslRepository;

    public Page<User> pageUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public List<User> getGuestUsers() {

        BooleanExpression expression = QUserRole.userRole.userId.isNull();

        return userDslRepository.findUserByPredicate(expression);
    }

}
