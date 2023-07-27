package com.lianglliu.springbootquerydsl.repository.dsl;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.blazebit.persistence.querydsl.BlazeJPAQueryFactory;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class BlazeQueryDslQueryBase {
    protected BlazeJPAQueryFactory queryFactory;

    public BlazeQueryDslQueryBase(EntityManager entityManager, CriteriaBuilderFactory criteriaBuilderFactory) {
        this.queryFactory = new BlazeJPAQueryFactory(entityManager, criteriaBuilderFactory);
    }

    protected <T> Page<T> fetchPage(BlazeJPAQuery<T> query, Pageable pageable) {
        var pagedList = query.fetchPage(Long.valueOf(pageable.getOffset()).intValue(), pageable.getPageSize());

        return PageableExecutionUtils.getPage(new ArrayList<>(pagedList), pageable, pagedList::getTotalSize);
    }

    protected <T> Page<T> fetchPageBySort(BlazeJPAQuery<T> query, Pageable pageable, Sort sort) {
        return fetchPage(query.orderBy(buildOrderSpecs(sort)), pageable);
    }

    protected <T> Page<T> fetchPage(BlazeJPAQuery<T> query, Pageable pageable, OrderSpecifier<?>[] orderSpecifiers) {

        List<T> content = query.orderBy(orderSpecifiers)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return PageableExecutionUtils.getPage(content, pageable, query::fetchCount);
    }

    protected <T> Page<T> fetchPage1(BlazeJPAQuery<T> query, Pageable pageable, OrderSpecifier<?>[] orderSpecifiers) {
        return fetchPage(query.orderBy(orderSpecifiers), pageable);
    }

    private OrderSpecifier<?>[] buildOrderSpecs(Sort sort) {
        if (sort.isUnsorted()) {
//            return new OrderSpecifier<?>[0];
            throw new RuntimeException("使用BlazeJPAQuery作为分页时，mysql来说，必须要排序字段");
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
