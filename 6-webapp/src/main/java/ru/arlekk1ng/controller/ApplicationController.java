package ru.arlekk1ng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.arlekk1ng.service.BlogService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationController {
    private final BlogService blogService;

    private final String MAIN_ENTRY = "day-all";

    public ApplicationController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * Возвращает шаблон с выбранным днем из блога.
     * @param entry
     * @param page
     * @return
     */
    @PostMapping("/blog")
    public String blog(
            @RequestParam String entry,
            Model page
    ) {
        if (entry.isEmpty() || entry.equals(MAIN_ENTRY)) {
            return blog(page);
        }

        List<String> blogDaysStrings = new ArrayList<>(
                List.of(blogService.getBlogDays(true))
        );
        blogDaysStrings.add(0, MAIN_ENTRY);
        blogDaysStrings.remove(entry);

        page.addAttribute("entries", blogDaysStrings);
        page.addAttribute("day", entry);
        return "one-day-blog.html";
    }

    @GetMapping("/blog")
    public String blog(Model page) {
        page.addAttribute("entries", blogService.getBlogDays(true));
        return "all-days-blog.html";
    }

//    /**
//     * Возвращает шаблон с днем из блога, если он присутствует.
//     * В ином случае, возвращает шаблон со всеми днями блога.
//     * @param search
//     * @param page
//     * @return
//     */
//    @PostMapping("/blog")
//    public String blog(
//            @RequestParam String search,
//            Model page
//    ) {
//        search = search.toLowerCase();
//        String[] blogDays = blogService.getBlogDays();
//
//        for (String day: blogDays) {
//            if (search.contains(day) && search.contains("day")) {
//                page.addAttribute("day", "day-" + day);
//                return "one-day-blog.html";
//            }
//        }
//
//        return blog(page);
//    }
}
