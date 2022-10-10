package com.example.rest_api_with_jwt.app.article.entity;

import com.example.rest_api_with_jwt.app.base.entity.BaseEntity;
import com.example.rest_api_with_jwt.app.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Article extends BaseEntity {
    @ManyToOne
    private Member author;
    private String subject;
    private String content;

    public Article(long id) {
        super(id);
    }
}