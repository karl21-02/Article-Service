package com.project.blog.domain.member.controller;

import com.project.blog.domain.email.service.EmailService;
import com.project.blog.domain.member.service.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private final MemberService memberService;
    @Autowired
    private final EmailService emailService;
    @PreAuthorize("isAnonymous()") // 로그인 안했을때만 쓰게 하는 것
    @GetMapping("/login")
    public String loginPage() {
        return "/member/login";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "/member/join";
    }

    @PostMapping("/join")
    public String join (@Valid JoinForm joinForm) {
        memberService.join(joinForm.getUsername(), joinForm.getPassword(), joinForm.getNickname(), joinForm.getEmail());

        // 회원 가입 후 메일 서비스 실행 -> 이를 위해서 의존성 주입 필요!
        // html 이메일로 보낼 때 인라인으로 해야한다.
        emailService.send(joinForm.getEmail(), "서비스 환영합니다", "회원가입 환영 메일");

        return "/member/login";
    }
    @Data
    @Getter
    @ToString
    @Setter
    public static class JoinForm {
        @NotNull
        @Length(min=4)
        private String username;
        @NotNull
        @Length(min=4)
        private String nickname;
        @NotNull
        @Length(min=4)
        private String email;
        @NotNull
        @Length(min=4)
        private String password;
        @NotNull
        @Length(min = 4)
        private String password_confirm;
    }
}
