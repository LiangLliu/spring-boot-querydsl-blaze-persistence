# spring boot query dsl blaze persistence

## 扩展 Repository

* Repository同时实现该接口 org.springframework.data.querydsl.QuerydslPredicateExecutor<T> 之后，便可以通过Predicate查询

eg:

```java

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>, QuerydslPredicateExecutor<Role> {
}
```

```java

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository repository;

    public Page<Role> pageRoles(String role, Pageable pageable) {
        Predicate predicate = QRole.role.name.contains(role);
        return repository.findAll(predicate, pageable);
    }

}
```

## JPAQuery查询模式

* 可以使用可读性很高的join语句像写sql一样写出查询逻辑，最后再Fetch

### 组合条件查询

```java

@RestController
@RequestMapping("/dsl/users")
@RequiredArgsConstructor
public class UserDslController {
    private final UserDslService userService;

    @GetMapping("/roles")
    public List<User> findUserByRoleAndUsername(
            @RequestParam(name = "role") String role,
            @RequestParam(name = "username", required = false) String username
    ) {
        return userService.findUserByRoleAndUsername(role, username);
    }

}

```

```java

@Service
@RequiredArgsConstructor
public class UserDslService {
    private final UserDslRepository userDslRepository;

    public List<User> findUserByRoleAndUsername(String role, String username) {
        Predicate predicate = QRole.role.name.contains(role);
        if (!ObjectUtils.isEmpty(username)) {
            predicate = new BooleanBuilder(predicate)
                    .and(QUser.user.username.contains(username));
        }

        return userDslRepository.findUserByPredicate(predicate);
    }
}
```

```java

@Repository
public class UserDslRepository extends BlazeQueryDslQueryBase {

    public UserDslRepository(EntityManager entityManager, CriteriaBuilderFactory criteriaBuilderFactory) {
        super(entityManager, criteriaBuilderFactory);
    }

    public List<User> findUserByPredicate(Predicate predicate) {

        return this.queryFactory.selectFrom(user)
                .leftJoin(userRole)
                .on(userRole.userId.eq(user.id))
                .leftJoin(QRole.role)
                .on(QRole.role.id.eq(userRole.roleId))
                .where(predicate)
                .select(
                        user
                )
                .distinct()
                .fetch();
    }
}

```


