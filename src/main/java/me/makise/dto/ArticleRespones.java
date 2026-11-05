package me.makise.dto;

import lombok.Getter;

public class ArticleRespones {
    @Getter
    public class
    private String title;
    private String content;

    public ArticleResponse(Article entity){
        this.title = entity.getTitle();
        this.

    }
}
