
package me.makise.controller;


import lombok.RequiredArgsConstructor;
import me.makise.domain.Article;
import me.makise.dto.AddArticleRequest;
import me.makise.dto.ArticleResponse;
import me.makise.dto.UpdateArticleRequest;
import me.makise.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ArticleResponse(savedArticle));

    }


    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> getAllArticles() {
        List<ArticleResponse> articleResponseList = blogService.getAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok().body(articleResponseList);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> getArticle(
            @PathVariable Long id) {


        return ResponseEntity.ok()
                .body(new ArticleResponse(blogService.findById(id)));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable long id,
                                                         @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.updateArticle(id, request);
        return ResponseEntity.ok()
                .body(new ArticleResponse(updatedArticle));
    }
}
