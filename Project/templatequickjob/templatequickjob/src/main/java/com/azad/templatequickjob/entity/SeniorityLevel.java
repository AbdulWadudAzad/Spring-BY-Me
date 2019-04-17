package com.azad.templatequickjob.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class SeniorityLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    @Size(min = 2,max = 20,message = "Seniority Level must be between 2 and 20 Characters")
    private String seniority;

    public SeniorityLevel() {
    }
    public SeniorityLevel(Long id) {
        this.id=id;
    }

    public SeniorityLevel(String seniority) {
        this.seniority = seniority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }
}
