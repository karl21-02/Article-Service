package com.project.blog.domain.like.Service;

import com.project.blog.domain.article.Article;
import com.project.blog.domain.article.ArticleRepository;
import com.project.blog.domain.like.Entity.ArticleLikeEntity;
import com.project.blog.domain.like.Repository.ArticleLikeRepository;
import com.project.blog.domain.member.entity.Member;
import com.project.blog.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.lock.OptimisticForceIncrementLockingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleLikeService {
    @Autowired
    private final ArticleLikeRepository articleLikeRepository;
    @Autowired
    private final ArticleRepository articleRepository;
    @Autowired
    private final MemberRepository memberRepository;

    @Transactional
    public void saveLikes(Long article_id, Long user_id) {
        Optional<Member> member = memberRepository.findById(user_id);
        Optional<Article> article = articleRepository.findById(article_id);

        articleLikeRepository.save(ArticleLikeEntity.builder()
                        .memberId(member.get().getId())
                        .articleId(article.get().getId())
                        .build()
        );
    }

    @Transactional
    public void deleteLikes(ArticleLikeEntity articleLikeEntity) {
        articleLikeRepository.delete(articleLikeEntity);
    }

    @Transactional
    public ArticleLikeEntity findLikes(Long member_id, Long article_id) {
        return articleLikeRepository.findByMemberIdAndArticleId(member_id, article_id);
    }

    @Transactional
    public List<ArticleLikeEntity> findLikesByArticleId(Long articleId) {
        return this.articleLikeRepository.findAllByArticleId(articleId);
    }
}
