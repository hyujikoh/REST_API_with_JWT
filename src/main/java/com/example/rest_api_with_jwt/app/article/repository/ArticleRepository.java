package com.example.rest_api_with_jwt.app.article.repository;

import com.example.rest_api_with_jwt.app.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByIdDesc();
}