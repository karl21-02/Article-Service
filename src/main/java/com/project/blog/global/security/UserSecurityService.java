package com.project.blog.global.security;

import com.project.blog.domain.member.entity.Member;
import com.project.blog.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 멤버 레포지터리와 연계하여, 유저 이름이 데이터베이스에 있는지 확인 !
 */
@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {
    private final MemberRepository memberRepository;

    /**
     * 클라이언트 이름 조회 시 인증 여부
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> _user = this.memberRepository.findByusername(username);

        if (_user.isEmpty()) {
            throw new UsernameNotFoundException("유저를 찾을 수 없습니다.");
        }

        Member member = _user.get();
        List<GrantedAuthority> authorityList = new ArrayList<>();

        return new User(member.getUsername(), member.getPassword(), authorityList);
    }

}
