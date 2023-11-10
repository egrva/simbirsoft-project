package ru.aegorova.simbirsoftproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.aegorova.simbirsoftproject.dto.SignUpDto;
import ru.aegorova.simbirsoftproject.services.SignUpService;

@Controller
public class SignUpController {

    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "registration";
    }

    @PostMapping("/signUp")
    public String signUp(SignUpDto dto) {
        signUpService.signUp(dto);
        return "redirect:/login";
    }
}
