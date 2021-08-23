package com.example.helpingout.controllers;

import com.example.helpingout.models.Tag;
import com.example.helpingout.models.User;
import com.example.helpingout.models.dto.UserTagDTO;
import com.example.helpingout.repositories.TagRepository;
import com.example.helpingout.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

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
        return "redirect:/login";
    }

    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer userId, Model model){
        Optional<User> result = userRepository.findById(userId);
        User user = result.get();
        model.addAttribute("title", "Add Tag to: " + user.getUsername());
        model.addAttribute("tags", tagRepository.findAll());
        UserTagDTO userTag = new UserTagDTO();
        userTag.setUser(user);
        model.addAttribute("userTag", userTag);
        //TODO THIS VIEW NEEDS TO BE CREATED. See Java 18.5.5 video at 7:53
        return "add-tag.html";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid UserTagDTO userTag,
                                    Errors errors, Model model){
        if (!errors.hasErrors()) {
            User user = userTag.getUser();
            Tag tag = userTag.getTag();
            if (!user.getTags().contains(tag)) {
                user.addTag(tag);
                userRepository.save(user);
            }
            // TODO: NOT SURE WHERE TO REDIRECT, as of now, "detail" is NOT a created view. "User-home" the same thing?
            return "redirect:detail?userId=" + user.getId();
        }
        return "redirect:user-home";
    }

}
