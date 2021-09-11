package com.example.helpingout.controllers;

import com.example.helpingout.models.Tag;
import com.example.helpingout.repositories.TagRepository;
import com.example.helpingout.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
// This request mapping may not be required.
@RequestMapping
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    // This method shows all tags/causes.
    @GetMapping("/admin-tags")
    public String displayTags(Model model) {
        model.addAttribute("title", "All Tags");
        model.addAttribute("tags", tagRepository.findAll());
        // This return may be different depending on the tag views.
        return "admin-tag-index";
    }

    // This method displays the form for creating a new tag/cause.
    @GetMapping("/create")
    public String displayCreateTagForm(Model model) {
        model.addAttribute("title", "Create Tag");
        model.addAttribute(new Tag());
        // Depending on name of template for this view.
        return "create-tag";
    }

    // This method processes the form, creating the new tag/cause.
    @PostMapping("create")
    public String processCreateTagForm(@ModelAttribute @Valid Tag tag,
                                       Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Tag");
            model.addAttribute(tag);
            return "tags/create";
        }

        tagRepository.save(tag);
        return "redirect:";
    }

    @GetMapping("/deleteTag")
    public String deleteTag(@RequestParam Integer tagId) {
        tagRepository.deleteById(tagId);
        return "redirect:admin/tags/index";
    }
}
