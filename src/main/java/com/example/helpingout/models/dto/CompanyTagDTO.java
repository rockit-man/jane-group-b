package com.example.helpingout.models.dto;

import com.example.helpingout.models.Company;
import com.example.helpingout.models.Tag;

import javax.validation.constraints.NotNull;

public class CompanyTagDTO {

    @NotNull
    private Company company;

    @NotNull
    private Tag tag;

    public CompanyTagDTO() {}

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
