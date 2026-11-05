package me.makise.controller;

import lombok.RequiredArgsConstructor;
import me.makise.domain.Article;
import me.makise.dto.AddArticleRequest;
import me.makise.dto.ArticleRespones;
import me.makise.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;


@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);

    }


    @GetMapping("/api/articles")
    public ResponseEntity<List<Article>> getAllArticles(){
        List<Article> articleList = blogService.getAll();
        List<ArticleRespones> articleResponesList = new ArrayList<>();
        for(Article article; articleList) {
            ArticleRespones ar = new ArticleRespones(article);
            articleResponesList.add(ar);
        }
        List<ArticleResponse> result = blodService.getAll()
                .stream()
                .map(ArticleResponse::new)Stream<ArticleRespones>
                .toList();
            return ResponseEntity.ok().body(articleList);
        }
    }
}