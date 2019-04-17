package com.azad.templatequickjob.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class EmploymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotEmpty(message = "Enter Employment Type")
    @Size(min = 2,max = 30,message = "Employment Type must be between 2 and 30 Characters")
    private String empType;

    public EmploymentType() {
    }

    public EmploymentType(Long id) {
        this.id=id;
    }

    public EmploymentType(String empType) {
        this.empType = empType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }
}
