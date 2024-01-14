package com.project.blog.domain.member.service;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.project.blog.domain.member.entity.Member;
import com.project.blog.domain.member.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     * @param username
     * @return
     */
    public Member join(String username, String password, String nickname, String email) {
        Member member = Member
                .builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .email(email)
                .build();

        return this.memberRepository.save(member);
    }

    @Transactional
    public Member whenSocialLogin(String providerTypeCode, String username, String nickname) {
        Optional<Member> opMember = findByUsername(username);

        if(opMember.isPresent()) {
            return opMember.get();
        }

        // 멤버가 존재하지 않는다면 가입
        return join(username, "", nickname, ""); // 최초 로그인시 1번
    }

    private Optional<Member> findByUsername(String username) {
        return memberRepository.findByusername(username);
    }


}
