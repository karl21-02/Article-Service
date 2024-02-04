package com.project.blog.global.initData.dev;

import com.project.blog.domain.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class Dev {
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * ApplicationRunner란 application구동 후 특정 데이터 확인을 위해 사용...! == 가데이터
     * @param memberService
     * @return
     */
    @Bean
    public ApplicationRunner init(MemberService memberService) {
        return args -> {
//            memberService.join("admin", "1234", "admin", "admin@test.com");
//            memberService.join("member1", "1234", "member1", "member1@test.com");
//            memberService.join("member2", "1234", "member2", "member2@test.com");
        };
    }
}