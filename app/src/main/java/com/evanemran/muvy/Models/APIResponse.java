package com.evanemran.muvy.Models;

import java.util.List;

public class APIResponse {
    List<Titles> titles = null;
    List<Titles> names = null;
    List<Titles> companies = null;

    public List<Titles> getTitles() {
        return titles;
    }

    public List<Titles> getNames() {
        return names;
    }

    public List<Titles> getCompanies() {
        return companies;
    }
}
