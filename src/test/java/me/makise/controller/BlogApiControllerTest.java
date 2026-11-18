package me.makise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.makise.domain.Article;
import me.makise.dto.AddArticleRequest;
import me.makise.dto.UpdateArticleRequest;
import me.makise.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BlogRepository blogRepository;

    @BeforeEach
    void cleanUp() {
        blogRepository.deleteAll();
    }

    private Article saveArticle(String title, String content) {
        return blogRepository.save(new Article(title, content));
    }

    @DisplayName("1. POST /api/articles : 新增一篇文章，应返回 201")
    @Test
    void addArticle() throws Exception {
        String title = "你好";
        String content = "我是靓仔";
        AddArticleRequest request = new AddArticleRequest(title, content);
        String requestBody = objectMapper.writeValueAsString(request);

        ResultActions result = mockMvc.perform(post("/api/articles")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.content").value(content));

        List<Article> articles = blogRepository.findAll();
        assertThat(articles).hasSize(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("2. GET /api/articles : 返回全部文章列表")
    @Test
    void getAllArticles() throws Exception {
        saveArticle("第一篇", "这是第一篇文章");
        saveArticle("第二篇", "这是第二篇文章");

        ResultActions result = mockMvc.perform(get("/api/articles"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("第一篇"))
                .andExpect(jsonPath("$[0].content").value("这是第一篇文章"))
                .andExpect(jsonPath("$[1].title").value("第二篇"))
                .andExpect(jsonPath("$[1].content").value("这是第二篇文章"));
    }

    @DisplayName("3. GET /api/articles/{id} : 按 id 查询单篇文章")
    @Test
    void getArticle() throws Exception {
        Article article = saveArticle("查询测试", "这一条用来测试单篇查询");

        ResultActions result = mockMvc.perform(get("/api/articles/{id}", article.getId()));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("查询测试"))
                .andExpect(jsonPath("$.content").value("这一条用来测试单篇查询"));
    }

    @DisplayName("4. DELETE /api/articles/{id} : 按 id 删除文章")
    @Test
    void deleteArticle() throws Exception {
        Article article = saveArticle("待删除的文章", "这一条马上会被删除");
        Long id = article.getId();
        assertThat(blogRepository.existsById(id)).isTrue();

        ResultActions result = mockMvc.perform(delete("/api/articles/{id}", id));

        result.andExpect(status().isOk());
        assertThat(blogRepository.existsById(id)).isFalse();
        assertThat(blogRepository.findAll()).isEmpty();
    }

    @DisplayName("5. PUT /api/articles/{id} : 按 id 修改文章内容")
    @Test
    void updateArticle() throws Exception {
        Article article = saveArticle("修改前的标题", "修改前的内容");
        Long id = article.getId();
        String newTitle = "修改后的标题";
        String newContent = "修改后的内容";
        UpdateArticleRequest request = new UpdateArticleRequest(newTitle, newContent);
        String requestBody = objectMapper.writeValueAsString(request);

        ResultActions result = mockMvc.perform(put("/api/articles/{id}", id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(newTitle))
                .andExpect(jsonPath("$.content").value(newContent));

        Article updated = blogRepository.findById(id).orElseThrow();
        assertThat(updated.getTitle()).isEqualTo(newTitle);
        assertThat(updated.getContent()).isEqualTo(newContent);
    }
}
