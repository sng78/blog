package io.github.sng78.blog.web.article;

import io.github.sng78.blog.model.Article;
import io.github.sng78.blog.repository.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

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

    @GetMapping("/blog/{id}")
    public String articleDetails(@PathVariable(value = "id") long id, Model model) {
        if (!repository.existsById(id)) {
            return "redirect:/blog";
        }
        model.addAttribute("title", "Блог сайта");
        Optional<Article> article = repository.findById(id);
        ArrayList<Article> result = new ArrayList<>();
        article.ifPresent(result::add);
        model.addAttribute("article", result);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String articleEdit(@PathVariable(value = "id") long id, Model model) {
        if (!repository.existsById(id)) {
            return "redirect:/blog";
        }
        model.addAttribute("title", "Редактирование статьи");
        Optional<Article> article = repository.findById(id);
        ArrayList<Article> result = new ArrayList<>();
        article.ifPresent(result::add);
        model.addAttribute("article", result);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogArticleUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String preview, @RequestParam String content) {
        Article article = repository.findById(id).orElseThrow();
        article.setTitle(title);
        article.setPreview(preview);
        article.setContent(content);
        repository.save(article);
        return "redirect:/blog";
    }
}
