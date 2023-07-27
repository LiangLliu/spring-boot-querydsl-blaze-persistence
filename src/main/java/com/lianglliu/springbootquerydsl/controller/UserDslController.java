package com.lianglliu.springbootquerydsl.controller;

import com.lianglliu.springbootquerydsl.constants.QueryConstants;
import com.lianglliu.springbootquerydsl.models.User;
import com.lianglliu.springbootquerydsl.models.pojo.UserPojo;
import com.lianglliu.springbootquerydsl.service.UserDslService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dsl/users")
@RequiredArgsConstructor
public class UserDslController {
    private final UserDslService userService;

    @GetMapping("/v1")
    public Page<User> pageUsersV1(@PageableDefault(size = QueryConstants.DEFAULT_PAGE_SIZE) Pageable pageable) {
        return userService.dslPageUsers(pageable);
    }

    @GetMapping("/v2")
    public Page<User> pageUsersV2(@PageableDefault(size = QueryConstants.DEFAULT_PAGE_SIZE) Pageable pageable) {
        return userService.dslPageUsers2(pageable);
    }

    @GetMapping("/pojos")
    public Page<UserPojo> pageUserPojos(@PageableDefault(size = QueryConstants.DEFAULT_PAGE_SIZE) Pageable pageable) {
        return userService.pageUserPojos(pageable);
    }

    @GetMapping("/roles")
    public List<User> findUserByRoleAndUsername(
            @RequestParam(name = "role") String role,
            @RequestParam(name = "username", required = false) String username
    ) {
        return userService.findUserByRoleAndUsername(role, username);
    }

}
