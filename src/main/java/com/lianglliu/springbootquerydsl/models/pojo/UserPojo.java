package com.lianglliu.springbootquerydsl.models.pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserPojo {
    private Long id;
    private String username;
    private String email;
    private String roleName;
    private String roleDescription;
}
