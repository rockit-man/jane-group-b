package com.example.helpingout.controllers;

import com.example.helpingout.models.Profile;
import com.example.helpingout.models.Tag;
import com.example.helpingout.models.dto.ProfileTagDTO;
import com.example.helpingout.repositories.TagRepository;
import com.example.helpingout.repositories.ProfileRepository;
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
public class ProfileController {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private TagRepository tagRepository;

    // find all users, create, delete
    @GetMapping("register")
    //"register" is placeholder for registration URI.
    public String displayRegistrationForm(Model model){
        model.addAttribute("title", "Profile Registration");
        model.addAttribute(new Profile());
        return "registration";
        //"registration" is placeholder for template.
    }

    @PostMapping("register")
    //"register" is placeholder for registration URI.
    public String processRegistrationForm(@ModelAttribute @Valid Profile newProfile,
                                          Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Profile Registration");
            return "registration";
            //"registration" is placeholder for template.
        }

        profileRepository.save(newProfile);
        return "redirect:/login";
    }

    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer profileId, Model model){
        Optional<Profile> result = profileRepository.findById(profileId);
        Profile profile = result.get();
        model.addAttribute("title", "Add Tag to: " + profile.getProfileName());
        model.addAttribute("tags", tagRepository.findAll());
        ProfileTagDTO profileTag = new ProfileTagDTO();
        profileTag.setUser(profile);
        model.addAttribute("profileTag", profileTag);
        // TODO THIS VIEW NEEDS TO BE CREATED. See Java 18.5.5 video at 7:53
        return "add-tag.html";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid ProfileTagDTO userTag,
                                    Errors errors, Model model){
        if (!errors.hasErrors()) {
            Profile profile = userTag.getUser();
            Tag tag = userTag.getTag();
            if (!profile.getTags().contains(tag)) {
                profile.addTag(tag);
                profileRepository.save(profile);
            }
            // TODO: NOT SURE WHERE TO REDIRECT, as of now, "detail" is NOT a created view. "Profile-home" the same thing?
            return "redirect:detail?userId=" + profile.getId();
        }
        return "redirect:user-home";
    }

}
