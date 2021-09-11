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
@RequestMapping("tags")
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    // This method shows all tags/causes.
    @GetMapping
    public String displayTags(Model model) {
        model.addAttribute("title", "All Tags");
        model.addAttribute("tags", tagRepository.findAll());
        // This return may be different depending on the tag views.
        return "tags/index";
    }

    // This method displays the form for creating a new tag/cause.
    @GetMapping("create")
    public String displayCreateTagForm(Model model) {
        model.addAttribute("title", "Create Tag");
        model.addAttribute(new Tag());
        // Depending on name of template for this view.
        return "tags/create";
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

    @GetMapping("delete")
    public String displayDeleteTagForm(Model model) {
        model.addAttribute("title", "Delete Tags");
        model.addAttribute("events", tagRepository.findAll());
        return "tags/delete";
    }

    @PostMapping("delete")
    public String processDeleteTagForm(@RequestParam(required = false) int[] tagIds) {

        if (tagIds != null) {
            for (int id : tagIds) {
                tagRepository.deleteById(id);
            }
        }

        return "redirect:";
    }
}
