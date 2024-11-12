package io.github.sng78.blog.web.article;

import io.github.sng78.blog.model.Article;
import io.github.sng78.blog.repository.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        model.addAttribute("title", "Добавить новую статью");
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogArticleAdd(@RequestParam String title, @RequestParam String preview, @RequestParam String content) {
        repository.save(new Article(title, content, preview));
        return "redirect:/blog";
    }
}
