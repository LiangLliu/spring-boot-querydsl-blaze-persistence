package com.lianglliu.springbootquerydsl.controller;

import com.lianglliu.springbootquerydsl.constants.QueryConstants;
import com.lianglliu.springbootquerydsl.models.Role;
import com.lianglliu.springbootquerydsl.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public Page<Role> pageRoles(
            @PageableDefault(size = QueryConstants.DEFAULT_PAGE_SIZE) Pageable pageable,
            @RequestParam(name = "role", required = false) String role
    ) {
        return roleService.pageRoles(role, pageable);
    }

}
