package com.project.blog.domain.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 메인 홈페이지 화면!
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home/main";
    }
}
