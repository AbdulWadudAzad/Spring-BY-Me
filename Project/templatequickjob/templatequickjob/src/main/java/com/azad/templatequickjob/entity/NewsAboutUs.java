package com.azad.templatequickjob.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class NewsAboutUs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    @Size(min = 2,max = 30,message = "Way of hear must be between 2 and 30 Characters")
    private String hearAboutUs;

    public NewsAboutUs() {
    }
    public NewsAboutUs(Long id) {
        this.id=id;
    }

    public NewsAboutUs(String hearAboutUs) {
        this.hearAboutUs = hearAboutUs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHearAboutUs() {
        return hearAboutUs;
    }

    public void setHearAboutUs(String hearAboutUs) {
        this.hearAboutUs = hearAboutUs;
    }
}
