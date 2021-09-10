package com.example.helpingout.controllers;

import com.example.helpingout.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TagController {

    @Autowired
    TagRepository tagRepository;


}
