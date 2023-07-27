package com.lianglliu.springbootquerydsl.runner;

import com.lianglliu.springbootquerydsl.models.Role;
import com.lianglliu.springbootquerydsl.models.User;
import com.lianglliu.springbootquerydsl.models.UserRole;
import com.lianglliu.springbootquerydsl.repository.RoleRepository;
import com.lianglliu.springbootquerydsl.repository.UserRepository;
import com.lianglliu.springbootquerydsl.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDataRunner implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Init Data .... ");

        List<Role> roles = List.of(
                Role.builder().id(1).name("ROLE_SUPER_ADMIN").description("description ROLE_SUPER_ADMIN").build(),
                Role.builder().id(2).name("ROLE_ADMIN").description("description ROLE_ADMIN").build(),
                Role.builder().id(3).name("ROLE_ADMIN_1").description("description ROLE_ADMIN_1").build(),
                Role.builder().id(4).name("ROLE_ADMIN_2").description("description ROLE_ADMIN_2").build(),
                Role.builder().id(5).name("ROLE_PROJECT_MANAGER").description("description ROLE_PROJECT_MANAGER").build(),
                Role.builder().id(6).name("ROLE_PM_1").description("description ROLE_PM_1").build(),
                Role.builder().id(7).name("ROLE_PM_2").description("description ROLE_PM_2").build(),
                Role.builder().id(8).name("ROLE_PM_3").description("description ROLE_PM_3").build(),
                Role.builder().id(9).name("ROLE_PM_4").description("description ROLE_PM_4").build(),
                Role.builder().id(10).name("ROLE_TESTER").description("description ROLE_TESTER").build(),
                Role.builder().id(11).name("ROLE_TESTER_1").description("description ROLE_TESTER_1").build(),
                Role.builder().id(12).name("ROLE_GUEST").description("description ROLE_GUEST").build()
        );
        roleRepository.saveAll(roles);


        List<User> users = List.of(
                User.builder().id(1L).username("张三").email("zhangsan@test.com").build(),
                User.builder().id(2L).username("李四").email("lisi@test.com").build(),
                User.builder().id(3L).username("王五").email("wangwu@test.com").build(),
                User.builder().id(4L).username("赵六").email("zhaoliu@test.com").build(),
                User.builder().id(5L).username("孙七").email("sunqi@test.com").build(),
                User.builder().id(6L).username("周八").email("zhouba@test.com").build()
        );
        userRepository.saveAll(users);



        List<UserRole> userRoles = List.of(
                UserRole.builder().userId(1L).roleId(1).build(),
                UserRole.builder().userId(1L).roleId(2).build(),
                UserRole.builder().userId(1L).roleId(3).build(),
                UserRole.builder().userId(1L).roleId(4).build(),
                UserRole.builder().userId(2L).roleId(3).build(),
                UserRole.builder().userId(2L).roleId(4).build(),
                UserRole.builder().userId(2L).roleId(5).build(),
                UserRole.builder().userId(2L).roleId(6).build(),
                UserRole.builder().userId(2L).roleId(7).build(),
                UserRole.builder().userId(3L).roleId(5).build(),
                UserRole.builder().userId(3L).roleId(6).build(),
                UserRole.builder().userId(3L).roleId(7).build(),
                UserRole.builder().userId(3L).roleId(8).build(),
                UserRole.builder().userId(4L).roleId(9).build(),
                UserRole.builder().userId(5L).roleId(10).build()
        );
        userRoleRepository.saveAll(userRoles);

        System.out.println("Init Data Done .... ");
    }
}
