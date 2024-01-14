package com.project.blog.domain.article;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Value("${custom.fileDirPath}") // 정의한 값이 밑에 변수에 자동 저장된다
    private String fileDirPath;

    public void create(String title, String content, MultipartFile thumbnail) {
        String thumbnailPath = fileDirPath; // 이 값을 db에 저장



        // 실제 파일 저장(d 드리아브에)
        try {
            thumbnail.transferTo(new File(fileDirPath +"/" + UUID.randomUUID().toString() +".jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // dp에 저장
        Article article = Article.builder()
                .title(title)
                .content(content)
                .thumbnailImg(thumbnailPath)
                .build();
        this.articleRepository.save(article);
    }

    public List<Article> getList() {
        return this.articleRepository.findAll();
    }
}
