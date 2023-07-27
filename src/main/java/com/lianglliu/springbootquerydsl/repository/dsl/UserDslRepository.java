package com.lianglliu.springbootquerydsl.repository.dsl;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.lianglliu.springbootquerydsl.models.QRole;
import com.lianglliu.springbootquerydsl.models.User;
import com.lianglliu.springbootquerydsl.models.pojo.UserPojo;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static com.lianglliu.springbootquerydsl.models.QRole.role;
import static com.lianglliu.springbootquerydsl.models.QUser.user;
import static com.lianglliu.springbootquerydsl.models.QUserRole.userRole;

@Repository
public class UserDslRepository extends BlazeQueryDslQueryBase {

    public UserDslRepository(EntityManager entityManager, CriteriaBuilderFactory criteriaBuilderFactory) {
        super(entityManager, criteriaBuilderFactory);
    }

    public Page<User> findAllUser(Pageable pageable) {

        var query = this.queryFactory.selectFrom(user)
                .select(user);

        OrderSpecifier<?>[] orderSpecifiers = new OrderSpecifier[]
                {
                        new OrderSpecifier<>(
                                Order.DESC, user.createdTime, OrderSpecifier.NullHandling.NullsLast)
                };

        return this.fetchPage(query, pageable, orderSpecifiers);
    }

    public Page<User> findAllUser2(Pageable pageable) {

        var query = this.queryFactory.selectFrom(user)
                .select(user);

        OrderSpecifier<?>[] orderSpecifiers = new OrderSpecifier[]
                {
                        new OrderSpecifier<>(Order.DESC, user.createdTime, OrderSpecifier.NullHandling.NullsLast),
                        new OrderSpecifier<>(Order.ASC, user.id)
                };

        return this.fetchPage1(query, pageable, orderSpecifiers);
    }

    public Page<UserPojo> pageUserPojos(Pageable pageable) {

        var query = this.queryFactory.selectFrom(user)
                .leftJoin(userRole)
                .on(userRole.userId.eq(user.id))
                .leftJoin(role)
                .on(role.id.eq(userRole.roleId))
                .select(
                        Projections.bean(
                                UserPojo.class,
                                user.id,
                                user.username,
                                user.email.as("email"),
                                role.name.as("roleName"),
                                role.description.as("roleDescription")
                        )
                )
                .orderBy(
                        user.createdTime.desc(),
                        user.id.asc()
                );

        return this.fetchPage(query, pageable);
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
