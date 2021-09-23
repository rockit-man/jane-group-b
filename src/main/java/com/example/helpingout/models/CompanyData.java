package com.example.helpingout.models;

import java.util.ArrayList;

public class CompanyData {

    public static ArrayList<Company> findByColumnAndValue(String column, String value, Iterable<Company> allCompanies) {

        ArrayList<Company> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<Company>) allCompanies;
        }

        if (column.equals("all")){
            results = findByValue(value, allCompanies);
            return results;
        }
        for (Company company : allCompanies) {

            String aValue = getFieldValue(company, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(company);
            }
        }

        return results;
    }

    public static String getFieldValue(Company company, String fieldName){
        String theValue;
        if (fieldName.equals("name")){
            theValue = company.getName();
        } else {
            theValue = company.getTags().toString();
        }

        return theValue;
    }

    public static ArrayList<Company> findByValue(String value, Iterable<Company> allCompanies) {
        String lower_val = value.toLowerCase();

        ArrayList<Company> results = new ArrayList<>();

        for (Company company : allCompanies) {

            if (company.getName().toLowerCase().contains(lower_val)) {
                results.add(company);
            } else if (company.getTags().toString().toLowerCase().contains(lower_val)) {
                results.add(company);
            } else if (company.toString().toLowerCase().contains(lower_val)) {
                results.add(company);
            }

        }

        return results;
    }
}
