package com.azad.templatequickjob.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class CompanyIndustry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotEmpty(message = "Enter City Name")
    @Size(min = 2,max = 30,message = "City name must be between 2 and 30 Characters")
    private String comIndustry;

    public CompanyIndustry() {
    }
    public CompanyIndustry(Long id) {
        this.id=id;
    }

    public CompanyIndustry(String comIndustry) {
        this.comIndustry = comIndustry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComIndustry() {
        return comIndustry;
    }

    public void setComIndustry(String comIndustry) {
        this.comIndustry = comIndustry;
    }
}
