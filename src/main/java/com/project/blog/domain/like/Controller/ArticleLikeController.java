package com.project.blog.domain.like.Controller;

import com.project.blog.domain.article.Article;
import com.project.blog.domain.like.Entity.ArticleLikeEntity;
import com.project.blog.domain.like.Service.ArticleLikeService;
import com.project.blog.domain.member.entity.Member;
import com.project.blog.domain.member.repository.MemberRepository;
import com.project.blog.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.Option;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/like")
public class ArticleLikeController {
    @Autowired
    private ArticleLikeService articleLikeService;
    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/click_like")
    public String goPost(@RequestParam(name = "articleId") Long article_id, @RequestParam(name = "nowPage") int nowPage, Principal principal, Model model) {
        Optional<Member> member = memberRepository.findByusername(principal.getName());

        ArticleLikeEntity articleLikeEntity = articleLikeService.findLikes(member.get().getId(), article_id);

        if(articleLikeEntity == null) {
            articleLikeService.saveLikes(article_id, member.get().getId());
        }
        else { // 좋아요 취소
            articleLikeService.deleteLikes(articleLikeEntity);
        }

        List<ArticleLikeEntity> likesList = articleLikeService.findLikesByArticleId(article_id);

        return "redirect:article/list?page=" + (nowPage - 1);
    }
}
