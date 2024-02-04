package com.project.blog.domain.article;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;


@Data
public class ArticleDto {
    private String title;
    private Long id;
    private String content;
    private String thumbnailImg;
    private Long viewCount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ArticleDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.thumbnailImg = article.getThumbnailImg();
        this.viewCount = article.getViewCount();
        this.createdDate = article.getCreatedDate();
        this.modifiedDate = article.getModifiedDate();
    }
}
