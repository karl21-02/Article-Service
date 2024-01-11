package com.project.blog;

import com.project.blog.domain.post.repository.PostRepository;
import com.project.blog.domain.post.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

	@Autowired // 한마디로 postService 클래스를 쓰겠다고 선언하는 것!
	PostService postService;	// 의존성 주입

	@Test
	void contextLoads() {
		// 게시글 50개 등록 테스트
		for(int i=0; i<=50; i++) {
			String title = String.format("제목 %d", i);
			String content = String.format("내용 %d", i);

			this.postService.create(title, content);
		}
	}

}
