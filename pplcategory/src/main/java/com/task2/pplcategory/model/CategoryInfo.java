package com.task2.pplcategory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryInfo {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Colour")
    private String colour;

    @JsonProperty("SubClasses")
    private List<CategoryInfo> subClasses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public List<CategoryInfo> getSubClasses() {
        return subClasses;
    }

    public void setSubClasses(List<CategoryInfo> subClasses) {
        this.subClasses = subClasses;
    }

}
