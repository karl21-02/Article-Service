package com.project.blog.domain.like.Repository;

import com.project.blog.domain.article.Article;
import com.project.blog.domain.like.Entity.ArticleLikeEntity;
import com.project.blog.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleLikeRepository extends JpaRepository<ArticleLikeEntity, Long> {
    ArticleLikeEntity findByMemberIdAndArticleId(Long memberId, Long articleId);

    List<ArticleLikeEntity> findAllByArticleId(Long articleId);
}
