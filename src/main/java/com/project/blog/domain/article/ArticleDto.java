package com.project.blog.domain.article;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private String thumbnailImg;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    public Article toArticle() {
        Article article = Article.builder()
                .id(this.id)
                .title(title)
                .content(content)
                .thumbnailImg(thumbnailImg)
                .build();
        return article;
    }

    @Builder
    public ArticleDto(Long id, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
