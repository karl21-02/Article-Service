package com.project.blog.domain.like.Entity;

import com.project.blog.domain.article.Article;
import com.project.blog.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.boot.context.properties.bind.DefaultValue;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "likes")
public class ArticleLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "memberId")
    private Long memberId;

    @Column(name = "articleId")
    private Long articleId;
}
