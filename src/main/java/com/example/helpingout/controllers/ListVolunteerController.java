package com.example.helpingout.controllers;

import com.example.helpingout.models.Volunteer;
import com.example.helpingout.models.VolunteerData;
import com.example.helpingout.repositories.CompanyRepository;
import com.example.helpingout.repositories.TagRepository;
import com.example.helpingout.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping(value = "list-volunteers")
public class ListVolunteerController {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListVolunteerController () {

        columnChoices.put("all", "All");
        columnChoices.put("tags", "Tags");

    }

    @RequestMapping("")
    public String list(Model model) {
        model.addAttribute("tags", tagRepository.findAll());
        return "list-volunteers";
    }

    @RequestMapping(value = "results")
    public String listVolunteersByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        Iterable<Volunteer> volunteers;
        if (column.toLowerCase().equals("all")){
            volunteers = volunteerRepository.findAll();
            model.addAttribute("title", "All Volunteers");
        } else {
            volunteers = VolunteerData.findByColumnAndValue(column, value, volunteerRepository.findAll());
            model.addAttribute("title", "Volunteers with " + columnChoices.get(column) + ": " + value);
        }
        model.addAttribute("volunteers", volunteers);

        return "results";
    }

    @RequestMapping(value = "view/{volunteerId}")
    public String displayViewVolunteer(Model model, @PathVariable int volunteerId) {
        Volunteer volunteerObj = volunteerRepository.findById(volunteerId).orElse(new Volunteer());
        model.addAttribute("volunteer", volunteerObj);

        return "view";
    }
}
