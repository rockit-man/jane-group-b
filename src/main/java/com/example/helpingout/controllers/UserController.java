package com.example.helpingout.controllers;

import com.example.helpingout.models.Tag;
import com.example.helpingout.models.User;
import com.example.helpingout.models.dto.UserTagDTO;
import com.example.helpingout.repositories.TagRepository;
import com.example.helpingout.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private SecurityUserDetailsService userDetailsManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String displayRegistration(Model model) {
        model.addAttribute(new User());
        return "/registration";
    }

    @PostMapping(
            value = "/registration",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public void addUser(@RequestParam Map<String, String> body) {
        User user = new User();
        user.setUsername(body.get("username"));
        user.setPassword(passwordEncoder.encode(body.get("password")));
        user.setAccountNonLocked(true);
        user.setFirstname(body.get("firstName"));
        user.setLastname(body.get("lastName"));
        user.setEmail(body.get("email"));
        user.setOrg(false);
        userDetailsManager.createUser(user);
    }
    private String getErrorMessage(HttpServletRequest request, String key) {
        Exception exception = (Exception) request.getSession().getAttribute(key);
        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username and password!";
        }
        return error;
    }

//    @GetMapping("add-tag")
//    public String displayAddTagForm(@RequestParam Integer userId, Model model){
//        Optional<User> result = userRepository.findById(userId);
//        User user = result.get();
//        model.addAttribute("title", "Add Tag to: " + user.getUsername());
//        model.addAttribute("tags", tagRepository.findAll());
//        UserTagDTO userTag = new UserTagDTO();
//        userTag.setUser(user);
//        model.addAttribute("userTag", userTag);
//        // TODO THIS VIEW NEEDS TO BE CREATED. See Java 18.5.5 video at 7:53
//        return "add-tag.html";
//    }
//
//    @PostMapping("add-tag")
//    public String processAddTagForm(@ModelAttribute @Valid UserTagDTO userTag,
//                                    Errors errors, Model model){
//        if (!errors.hasErrors()) {
//            User user = userTag.getUser();
//            Tag tag = userTag.getTag();
//            if (!user.getTags().contains(tag)) {
//                user.addTag(tag);
//                userRepository.save(user);
//            }
//            // TODO: NOT SURE WHERE TO REDIRECT, as of now, "detail" is NOT a created view. "User-home" the same thing?
//            return "redirect:detail?userId=" + user.getId();
//        }
//        return "redirect:user-home";
//    }

}
