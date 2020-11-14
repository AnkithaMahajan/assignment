package com.task2.pplcategory.model;

import javax.persistence.*;

@Entity
@Table(name="CATEGORY_DATA")
public class DataPO {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="PARENT_ID")
    private long parentId;

    @Column(name="NAME")
    private String name;

    @Column(name="COLOUR")
    private String colour;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

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
}
