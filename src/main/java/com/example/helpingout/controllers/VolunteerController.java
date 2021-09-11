package com.example.helpingout.controllers;

import com.example.helpingout.models.Volunteer;
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

//    @GetMapping("detail")
//    public String displayEventDetails(@RequestParam Integer eventId, Model model) {
//
//        Optional<Event> result = eventRepository.findById(eventId);
//
//        if (result.isEmpty()) {
//            model.addAttribute("title", "Invalid Event ID: " + eventId);
//        } else {
//            Event event = result.get();
//            model.addAttribute("title", event.getName() + " Details");
//            model.addAttribute("event", event);
//            model.addAttribute("tags", event.getTags());
//        }
//
//        return "events/detail";
//    }
//
//    // responds to /events/add-tag?eventId=13
//    @GetMapping("add-tag")
//    public String displayAddTagForm(@RequestParam Integer eventId, Model model){
//        Optional<Event> result = eventRepository.findById(eventId);
//        Event event = result.get();
//        model.addAttribute("title", "Add Tag to: " + event.getName());
//        model.addAttribute("tags", tagRepository.findAll());
//        EventTagDTO eventTag = new EventTagDTO();
//        eventTag.setEvent(event);
//        model.addAttribute("eventTag", eventTag);
//        return "events/add-tag.html";
//    }
//
//    @PostMapping("add-tag")
//    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag,
//                                    Errors errors,
//                                    Model model){
//
//        if (!errors.hasErrors()) {
//            Event event = eventTag.getEvent();
//            Tag tag = eventTag.getTag();
//            if (!event.getTags().contains(tag)){
//                event.addTag(tag);
//                eventRepository.save(event);
//            }
//            return "redirect:detail?eventId=" + event.getId();
//        }
//
//        return "redirect:add-tag";
//    }
}
