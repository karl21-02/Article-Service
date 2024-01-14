package com.project.blog.domain.article;

import com.project.blog.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity // DB 테이블과 매핑 대상 !
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@ToString
public class Article extends BaseEntity {
    private String title;
    private String content;
    private String thumbnailImg;
}
