package ru.aegorova.simbirsoftproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {

    @GetMapping("/login")
    public String getSignInPage() {
        return "login";
    }
}
