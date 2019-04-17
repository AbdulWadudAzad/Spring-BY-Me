package com.azad.templatequickjob.entity;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotEmpty(message = "Enter Division Name")
    @Size(min = 2,max = 20,message = "Division name must be between 2 and 20 Characters")
    private String divisionName;

    public Division() {
    }
    public Division(Long id) {
        this.id=id;
    }

    public Division(String divisionName) {
        this.divisionName = divisionName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
}

