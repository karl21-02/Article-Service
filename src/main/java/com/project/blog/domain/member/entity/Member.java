package com.project.blog.domain.member.entity;

import com.project.blog.global.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

@Entity // DB 테이블과 매핑 대상 !
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@ToString
public class Member extends BaseEntity {
    @Comment("유저아이디")
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String nickname;
    private String email;
}
