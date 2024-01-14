package com.project.blog.domain.article;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@AllArgsConstructor // private 변수가 자동으로 의존성 주입!
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Article> articleList = this.articleService.getList();
        model.addAttribute("articleList", articleList); // view에 보여줄때 th 값으로 attributeName 사용 가능

        return "article/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(name="id") Long id, Model model) {
        Article article = this.articleService.getArticle(id);
        model.addAttribute("article", article); // view에 보여줄때 th 값으로 attributeName 사용 가능

        return "article/detail";
    }

    @GetMapping("/detail")
    public String detail(Model model) {
        model.addAttribute(new Article("a", (long)1, "b", ""));
        return "article/detail";
    }
    @GetMapping("/create_form")
    public String create() {
        return "article/create_form";
    }
    /**
     * 매개변수로 RequesParam이 있는데, 이건 name=title인 input 값 저장!
     * @return
     */
    @PostMapping("/create")
    public String create(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("thumbnail") MultipartFile thumbnail, Model model) {
        articleService.create(title, content, thumbnail);

        List<Article> articleList = this.articleService.getList();
        model.addAttribute("articleList", articleList); // view에 보여줄때 th 값으로 attributeName 사용 가능

        return "redirect:/article/list";
    }
}
