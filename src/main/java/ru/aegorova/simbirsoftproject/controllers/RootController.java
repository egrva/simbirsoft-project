package ru.aegorova.simbirsoftproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/main")
    public String getMainPage() {
        return "main";
    }
}
