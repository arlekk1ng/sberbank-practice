package ru.arlekk1ng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

    @RequestMapping("/blog")
    public String blog() {
        return "blog.html";
    }
}
