package com.evanemran.muvy.Models;

import java.util.List;

public class DetailsResponse {
    String id = "";
    String title = "";
    String year = "";
    String length = "";
    String rating = "";
    String rating_votes = "";
    String poster = "";
    String plot = "";
    Trailer trailer = null;
    List<Cast> cast = null;
    List<List<String>> technical_specs = null;


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getLength() {
        return length;
    }

    public String getRating() {
        return rating;
    }

    public String getRating_votes() {
        return rating_votes;
    }

    public String getPoster() {
        return poster;
    }

    public String getPlot() {
        return plot;
    }

    public Trailer getTrailer() {
        return trailer;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public List<List<String>> getTechnical_specs() {
        return technical_specs;
    }
}
