package com.lianglliu.springbootquerydsl.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.OffsetDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 5742738339395438276L;

    @Column(name = "created_time", nullable = false, updatable = false)
    @CreatedDate
    private OffsetDateTime createdTime;

    @Column(name = "updated_time", nullable = false)
    @LastModifiedDate
    private OffsetDateTime updatedTime;
}