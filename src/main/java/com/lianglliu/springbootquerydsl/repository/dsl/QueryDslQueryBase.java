package com.lianglliu.springbootquerydsl.repository.dsl;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.stream.Collectors;


public class QueryDslQueryBase {
    protected JPAQueryFactory queryFactory;

    public QueryDslQueryBase(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    protected <T> Page<T> fetchPage(JPAQuery<T> query, Pageable pageable) {
        return fetchPage(query, pageable, Sort.unsorted());
    }

    protected <T> Page<T> fetchPage(JPAQuery<T> query, Pageable pageable, OrderSpecifier<?>[] orderSpecifiers) {
        var results = query.offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orderSpecifiers)
                .fetchResults();
        return PageableExecutionUtils.getPage(results.getResults(), pageable, results::getTotal);
    }

    protected <T> Page<T> fetchPage(JPAQuery<T> query, Pageable pageable, Sort sort) {
        return fetchPage(query, pageable, buildOrderSpecs(sort));
    }

    private OrderSpecifier<?>[] buildOrderSpecs(Sort sort) {
        if (sort.isUnsorted()) {
            return new OrderSpecifier[0];
        }
        return sort.map(order -> new OrderSpecifier<>(
                        order.getDirection().isAscending()
                                ? Order.ASC
                                : Order.DESC,
                        Expressions.stringPath(order.getProperty()),
                        OrderSpecifier.NullHandling.NullsFirst
                ))
                .get()
                .collect(Collectors.toList())
                .toArray(new OrderSpecifier<?>[]{});
    }
}
