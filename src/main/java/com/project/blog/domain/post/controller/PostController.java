package com.project.blog.domain.post.controller;

import com.project.blog.domain.post.entity.Post;
import com.project.blog.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Post> postList = this.postService.getList();

        model.addAttribute("postList", postList);

        return "/post/list";
    }
}
