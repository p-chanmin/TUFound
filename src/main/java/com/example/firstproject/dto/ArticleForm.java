package com.example.firstproject.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {

    private String title;
    private String content;

    private String lat;

    private String lng;

    private Long id;


    public Article toEntity() {

        return new Article(id,title,content,lat,lng);
    }
}
