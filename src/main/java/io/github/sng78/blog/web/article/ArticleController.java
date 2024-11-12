package io.github.sng78.blog.web.article;

import io.github.sng78.blog.model.Article;
import io.github.sng78.blog.repository.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {

    private final ArticleRepository repository;

    public ArticleController(ArticleRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/blog")
    public String blog(Model model) {
        model.addAttribute("title", "Блог сайта");
        Iterable<Article> articles = repository.findAll();
        model.addAttribute("articles", articles);
        return "blog";
    }
}
