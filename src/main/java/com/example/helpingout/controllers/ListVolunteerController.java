package com.example.helpingout.controllers;

import com.example.helpingout.repositories.CompanyRepository;
import com.example.helpingout.repositories.TagRepository;
import com.example.helpingout.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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


}
