package com.lianglliu.springbootquerydsl.models.pojo;

import com.lianglliu.springbootquerydsl.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserRoleListPojo {
    private Long id;
    private String username;
    private String email;
    private List<Role> roles;
}
