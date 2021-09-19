package com.example.helpingout.controllers;

import com.example.helpingout.models.Company;
import com.example.helpingout.models.CompanyData;
import com.example.helpingout.models.Volunteer;
import com.example.helpingout.models.VolunteerData;
import com.example.helpingout.repositories.CompanyRepository;
import com.example.helpingout.repositories.TagRepository;
import com.example.helpingout.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping(value = "list-companies")
public class ListCompanyController {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private CompanyRepository companyRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListCompanyController () {

        columnChoices.put("all", "All");
        columnChoices.put("tags", "Tags");

    }

    @RequestMapping("")
    public String list(Model model) {
        model.addAttribute("tags", tagRepository.findAll());
        return "list-companies";
    }

    @RequestMapping(value = "company-results")
    public String listCompaniesByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        Iterable<Company> companies;
        if (column.toLowerCase().equals("all")){
            companies = companyRepository.findAll();
            model.addAttribute("title", "All Companies");
        } else {
            companies = CompanyData.findByColumnAndValue(column, value, companyRepository.findAll());
            model.addAttribute("title", "Companies with " + columnChoices.get(column) + ": " + value);
        }
        model.addAttribute("companies", companies);

        return "company-results";
    }

    @RequestMapping(value = "company-view/{companyId}")
    public String displayViewCompany(Model model, @PathVariable int companyId) {
        Company companyObj = companyRepository.findById(companyId).orElse(new Company());
        model.addAttribute("company", companyObj);

        return "company-view";
    }
}
