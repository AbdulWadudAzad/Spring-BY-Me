package com.azad.templatequickjob.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class YearExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private String year;

    public YearExperience() {
    }

    public YearExperience(String year) {
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
