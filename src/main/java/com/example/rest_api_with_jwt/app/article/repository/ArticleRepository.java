package com.example.rest_api_with_jwt.app.article.repository;

import com.example.rest_api_with_jwt.app.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}