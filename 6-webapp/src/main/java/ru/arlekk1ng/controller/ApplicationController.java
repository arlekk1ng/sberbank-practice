package ru.arlekk1ng.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.arlekk1ng.entity.BlogPost;
import ru.arlekk1ng.service.BlogPostsService;

@Controller
public class ApplicationController {
    private final BlogPostsService blogPostsService;
    private final BlogPost MAIN = new BlogPost("main", "На главную");

    public ApplicationController(BlogPostsService blogPostsService) {
        this.blogPostsService = blogPostsService;
    }

    @PostMapping("/blog")
    public String blog(
            @RequestParam String fragmentName,
            Model page
    ) {
        if (fragmentName.equals(MAIN.getFragmentName()) || fragmentName.isEmpty()) {
            return blog(page);
        }

        List<BlogPost> blogPosts = blogPostsService.findAll();
        blogPosts.removeIf(blogPost -> blogPost.getFragmentName().equals(fragmentName));
        blogPosts.add(0, MAIN);

        page.addAttribute("posts", blogPosts);
        page.addAttribute("fragment", fragmentName);

        return "blog-post.html";
    }

    @GetMapping("/blog")
    public String blog(Model page) {
        page.addAttribute("posts", blogPostsService.findAll());
        return "blog-posts.html";
    }
}
