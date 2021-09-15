package com.example.helpingout.controllers;

import com.example.helpingout.repositories.OrgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrgController {

    @Autowired
    OrgRepository orgRepository;
}
