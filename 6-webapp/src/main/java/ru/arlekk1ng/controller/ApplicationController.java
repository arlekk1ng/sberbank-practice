package ru.arlekk1ng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.arlekk1ng.service.BlogService;

@Controller
public class ApplicationController {
    private final BlogService blogService;

    public ApplicationController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * Возвращает шаблон с днем из блога, если он присутствует.
     * В ином случае, возвращает шаблон со всеми днями блога.
     * @param search
     * @param page
     * @return
     */
    @PostMapping("/blog")
    public String blog(
            @RequestParam String search,
            Model page
    ) {
        search = search.toLowerCase();
        String[] blogDays = blogService.getBlogDays();

        for (String day: blogDays) {
            if (search.contains(day) && search.contains("day")) {
                page.addAttribute("day", "day-" + day);
                return "one-day-blog.html";
            }
        }

        return blog(page);
    }

    @GetMapping("/blog")
    public String blog(Model page) {
        return "all-days-blog.html";
    }

//    @PostMapping("/blog")
//    public String blog(
//            @RequestParam String search,
//            Model page
//    ) {
//        return search + "-template.html";
//    }
//
//    @GetMapping("/blog")
//    public String blog(Model page) {
//        return "blog-template.html";
//    }

//    @PostMapping("/home")
//    public String home(
//            @RequestParam String name,
//            Model page
//    ) {
//        page.addAttribute("username", name);
//        return "home-template";
//    }
//
//    @GetMapping("/home")
//    public String home(Model page) {
//        page.addAttribute("username", "ГетАноним");
//        return "home-template";
//    }

//    @RequestMapping("/home/{name}")
//    public String home(
//            @PathVariable String name,
//            Model page
//    ) {
//        page.addAttribute("username", name);
//        return "home-template.html";
//    }

//    @RequestMapping("/home")
//    public String home(
//            @RequestParam(required = false) String name,
//            Model page
//    ) {
//        page.addAttribute("username",
//                name != null ? name : "Аноним");
//        return "home-template.html";
//    }
}
