package com.project.blog.domain.article;

import com.nimbusds.oauth2.sdk.SuccessResponse;
import com.project.blog.domain.like.Entity.ArticleLikeEntity;
import com.project.blog.domain.like.Service.ArticleLikeService;
import com.project.blog.domain.member.entity.Member;
import com.project.blog.domain.member.repository.MemberRepository;
import com.project.blog.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Optionals;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor // private 변수가 자동으로 의존성 주입!
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private final ArticleService articleService;
    @Autowired
    private ArticleLikeService articleLikeService;
    @Autowired
    private MemberRepository memberRepository;

    // 페이지 요청이 올때 -> /article/paging?page=1
    @GetMapping("/list")
    public String pageList(Model model, @PageableDefault(page = 1, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Article> page_list = articleService.getList_page(pageable);

        // 페이지 블럭 처리
        // 1을 더해주는 이유는 pageable은 0부터라 1을 처리하려면 1을 더해서 시작해야한다.
        int nowPage = page_list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 9, page_list.getTotalPages());
        model.addAttribute("list", page_list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "article/list";
    }


    @PostMapping("/searchPage")
    public String searchPage(Model model, @RequestParam(name = "keyword") String searchWord) {
        if (searchWord.isEmpty()) {
            throw new RuntimeException("찾는 제목이 없습니다.");
        }

        List<Article> article = articleService.findArticleByTitle(searchWord);

        model.addAttribute("searchList", article);

        return "/article/searchPage";
    }

    @GetMapping("/searchPage")
    public String searchPage() {
        return "/article/searchPage";
    }


    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(name="id") Long id, Model model) {
        Article article = this.articleService.getArticle(id);
        model.addAttribute("article", article); // view에 보여줄때 th 값으로 attributeName 사용 가능

        // 여기서 조회수 기능을 구현하자.
        // article에 view를 증가
        this.articleService.view_incre(id);
        ArticleDto articleDto = this.articleService.viewCount(id);

        model.addAttribute("viewCount", articleDto);

        return "article/detail";
    }

    @GetMapping("/detail")
    public String detail() {
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
