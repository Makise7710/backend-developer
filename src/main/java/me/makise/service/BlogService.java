package me.makise.service;
import lombok.RequiredArgsConstructor;
import me.makise.domain.Article;
import me.makise.dto.AddArticleRequest;
import me.makise.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {
    //@Autowired
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest dto){
        return blogRepository.save(dto. toEntity());
    }
}