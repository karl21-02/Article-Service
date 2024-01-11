package com.project.blog.domain.post.service;

import com.project.blog.domain.post.entity.Post;
import com.project.blog.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    // 데이터 전체 갖오기
    public List<Post> getList() {
        return this.postRepository.findAll();
    }
    // 데이터 insert
    public void create(String title, String content) {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .build();
        this.postRepository.save(post);
    }

}
