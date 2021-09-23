package com.example.helpingout.controllers;

import com.example.helpingout.models.Tag;
import com.example.helpingout.models.Volunteer;
import com.example.helpingout.models.dto.VolunteerTagDTO;
import com.example.helpingout.repositories.TagRepository;
import com.example.helpingout.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public String displayVolunteers(Model model) {
        model.addAttribute("title", "All Volunteers");
        model.addAttribute("volunteers", volunteerRepository.findAll());
        return "volunteers/index";
    }

    @GetMapping("create")
    public String displayCreateVolunteerForm(Model model) {
        model.addAttribute("title", "Create Volunteer Profile");
        model.addAttribute(new Volunteer());
        return "volunteers/create";
    }

    @PostMapping("create")
    public String processCreateVolunteerForm(@ModelAttribute @Valid Volunteer newVolunteer,
                                         Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Volunteer Profile");
            return "volunteers/create";
        }

        volunteerRepository.save(newVolunteer);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteVolunteerForm(Model model) {
        model.addAttribute("title", "Delete Volunteers");
        model.addAttribute("volunteers", volunteerRepository.findAll());
        return "volunteers/delete";
    }

    @PostMapping("delete")
    public String processDeleteVolunteerForm(@RequestParam(required = false) int[] volunteerIds) {

        if (volunteerIds != null) {
            for (int id : volunteerIds) {
                volunteerRepository.deleteById(id);
            }
        }

        return "redirect:";
    }

    @GetMapping("detail")
    public String displayVolunteerDetails(@RequestParam Integer volunteerId, Model model) {

        Optional<Volunteer> result = volunteerRepository.findById(volunteerId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Volunteer ID: " + volunteerId);
        } else {
            Volunteer volunteer = result.get();
            model.addAttribute("title", volunteer.getUsername() + " Details");
            model.addAttribute("volunteer", volunteer);
            model.addAttribute("tags", volunteer.getTags());
        }

        return "volunteers/detail";
    }

    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer volunteerId, Model model){
        Optional<Volunteer> result = volunteerRepository.findById(volunteerId);
        Volunteer volunteer = result.get();
        model.addAttribute("title", "Add Tag to: " + volunteer.getUsername());
        model.addAttribute("tags", tagRepository.findAll());
        VolunteerTagDTO volunteerTag = new VolunteerTagDTO();
        volunteerTag.setVolunteer(volunteer);
        model.addAttribute("volunteerTag", volunteerTag);
        return "volunteers/add-tag";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid VolunteerTagDTO volunteerTag,
                                    Errors errors,
                                    Model model){

        if (!errors.hasErrors()) {
            Volunteer volunteer = volunteerTag.getVolunteer();
            Tag tag = volunteerTag.getTag();
            if (!volunteer.getTags().contains(tag)){
                volunteer.addTag(tag);
                volunteerRepository.save(volunteer);
            }
            return "redirect:detail?volunteerId=" + volunteer.getId();
        }

        return "redirect:add-tag";
    }
}
