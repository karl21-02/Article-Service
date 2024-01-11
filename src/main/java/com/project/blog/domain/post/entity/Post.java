package com.project.blog.domain.post.entity;

import com.project.blog.domain.member.entity.Member;
import com.project.blog.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity // DB 테이블과 매핑 대상 !
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@ToString
public class Post extends BaseEntity {
    private String title;
    private String content;
    @ManyToOne // Many - Post, One - Member, 작성자 1명이지만 작성할 수 있는 글은 여러가지!
    private Member author;

}
