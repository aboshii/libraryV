package com.library.springlibrary.controller;

import com.library.springlibrary.exceptions.UserAlreadyExistException;
import com.library.springlibrary.model.dto.UserRegisterDto;
import com.library.springlibrary.service.UserService;
import com.library.springlibrary.service.VisitCounter;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class WebsiteController {
    private final VisitCounter visitCounter;
    private final UserService userService;

    @GetMapping("/")
    String mainPage(Model model){
        visitCounter.visitCounterCountUp();
        return "index";
    }
    @GetMapping("/register")
    String registerPage(){
        return "register-form";
    }
    @PostMapping("/register")
    String register(UserRegisterDto userRegisterDto){
        try {
            userService.registerUser(userRegisterDto);

        } catch (UserAlreadyExistException e) {
            return "index";
        }
        return "register-confirmation";
    }
}
