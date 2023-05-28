package com.library.springlibrary.controller;

import com.library.springlibrary.service.VisitCounter;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class WebsiteController {
    private VisitCounter visitCounter;

    @GetMapping("/")
    String mainPage(Model model){
        visitCounter.visitCounterCountUp();
        return "index";
    }
}
