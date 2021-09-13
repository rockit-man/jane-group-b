package com.example.helpingout.controllers;

import com.example.helpingout.models.Company;
import com.example.helpingout.models.Tag;
import com.example.helpingout.models.Volunteer;
import com.example.helpingout.models.dto.CompanyTagDTO;
import com.example.helpingout.models.dto.VolunteerTagDTO;
import com.example.helpingout.repositories.CompanyRepository;
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
@RequestMapping("companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public String displayCompanies(Model model) {
        model.addAttribute("title", "All Companies");
        model.addAttribute("companies", companyRepository.findAll());
        return "companies/index";
    }

    @GetMapping("create")
    public String displayCreateCompanyForm(Model model) {
        model.addAttribute("title", "Create Company Profile");
        model.addAttribute(new Company());
        return "companies/create";
    }

    @PostMapping("create")
    public String processCreateCompanyForm(@ModelAttribute @Valid Company newCompany,
                                             Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Company Profile");
            return "companies/create";
        }

        companyRepository.save(newCompany);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteCompanyForm(Model model) {
        model.addAttribute("title", "Delete Companies");
        model.addAttribute("companies", companyRepository.findAll());
        return "companies/delete";
    }

    @PostMapping("delete")
    public String processDeleteCompanyForm(@RequestParam(required = false) int[] companyIds) {

        if (companyIds != null) {
            for (int id : companyIds) {
                companyRepository.deleteById(id);
            }
        }

        return "redirect:";
    }

    @GetMapping("detail")
    public String displayCompanyDetails(@RequestParam Integer companyId, Model model) {

        Optional<Company> result = companyRepository.findById(companyId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Company ID: " + companyId);
        } else {
            Company company = result.get();
            model.addAttribute("title", company.getName() + " Details");
            model.addAttribute("company", company);
            model.addAttribute("tags", company.getTags());
        }

        return "companies/detail";
    }

    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer companyId, Model model){
        Optional<Company> result = companyRepository.findById(companyId);
        Company company = result.get();
        model.addAttribute("title", "Add Tag to: " + company.getName());
        model.addAttribute("tags", tagRepository.findAll());
        CompanyTagDTO companyTag = new CompanyTagDTO();
        companyTag.setCompany(company);
        model.addAttribute("companyTag", companyTag);
        return "companies/add-tag";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid CompanyTagDTO companyTag,
                                    Errors errors,
                                    Model model){

        if (!errors.hasErrors()) {
            Company company = companyTag.getCompany();
            Tag tag = companyTag.getTag();
            if (!company.getTags().contains(tag)){
                company.addTag(tag);
                companyRepository.save(company);
            }
            return "redirect:detail?companyId=" + company.getId();
        }

        return "redirect:add-tag";
    }
}
