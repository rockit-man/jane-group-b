package com.example.helpingout.controllers;

import com.example.helpingout.models.User;
import com.example.helpingout.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // find all users, create, delete
    @GetMapping("register")
    //"register" is placeholder for registration URI.
    public String displayRegistrationForm(Model model){
        model.addAttribute("title", "User Registration");
        model.addAttribute(new User());
        return "registration";
        //"registration" is placeholder for template.
    }

    @PostMapping("register")
    //"register" is placeholder for registration URI.
    public String processRegistrationForm(@ModelAttribute @Valid User newUser,
                                          Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "User Registration");
            return "registration";
            //"registration" is placeholder for template.
        }

        userRepository.save(newUser);
        return "redirect:";
    }
}
