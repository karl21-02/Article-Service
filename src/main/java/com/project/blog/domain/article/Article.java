package com.project.blog.domain.article;

import com.project.blog.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity // DB 테이블과 매핑 대상 !
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@ToString
public class Article extends BaseEntity {
    private String title;
    private Long id;
    private String content;
    private String thumbnailImg;
    @Builder.Default
    private Long viewCount = Long.valueOf("0");

    public void update_viewCount() {
        this.viewCount += 1;
    }
}