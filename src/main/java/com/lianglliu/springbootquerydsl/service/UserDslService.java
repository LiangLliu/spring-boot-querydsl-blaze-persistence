package com.lianglliu.springbootquerydsl.service;

import com.lianglliu.springbootquerydsl.models.QRole;
import com.lianglliu.springbootquerydsl.models.QUser;
import com.lianglliu.springbootquerydsl.models.User;
import com.lianglliu.springbootquerydsl.models.pojo.UserPojo;
import com.lianglliu.springbootquerydsl.repository.dsl.UserDslRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDslService {
    private final UserDslRepository userDslRepository;

    public Page<User> dslPageUsers(Pageable pageable) {
        return userDslRepository.findAllUser(pageable);
    }

    public Page<UserPojo> pageUserPojos(Pageable pageable) {
        return userDslRepository.pageUserPojos(pageable);
    }

    public List<User> findUserByRoleAndUsername(String role, String username) {
        Predicate predicate = QRole.role.name.contains(role);
        if (!ObjectUtils.isEmpty(username)) {
            predicate = new BooleanBuilder(predicate)
                    .and(QUser.user.username.contains(username));
        }

        return userDslRepository.findUserByPredicate(predicate);
    }
}
