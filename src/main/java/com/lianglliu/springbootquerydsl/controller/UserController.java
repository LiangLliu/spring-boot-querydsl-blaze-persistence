package com.lianglliu.springbootquerydsl.controller;

import com.lianglliu.springbootquerydsl.constants.QueryConstants;
import com.lianglliu.springbootquerydsl.models.User;
import com.lianglliu.springbootquerydsl.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public Page<User> pageUsers(@PageableDefault(size = QueryConstants.DEFAULT_PAGE_SIZE) Pageable pageable) {
        return userService.pageUsers(pageable);
    }

    @GetMapping("/guest-users")
    public List<User> getGuestUsers() {
        return userService.getGuestUsers();
    }
}
