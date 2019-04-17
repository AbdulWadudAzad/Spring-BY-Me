package com.azad.templatequickjob.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotEmpty(message = "Enter Certification Name")
    @Size(min = 2,max = 80,message = "Certification name must be between 2 and 80 Characters")
    private String certificName;

    public Certification() {
    }
    public Certification(Long id) {
        this.id=id;
    }

    public Certification(String certificName) {
        this.certificName = certificName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCertificName() {
        return certificName;
    }

    public void setCertificName(String certificName) {
        this.certificName = certificName;
    }
}
