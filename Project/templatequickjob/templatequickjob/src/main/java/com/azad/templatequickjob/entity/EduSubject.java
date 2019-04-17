package com.azad.templatequickjob.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class EduSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    @Size(min = 2,max = 40,message = "skill name must be between 2 and 40 Characters")
    private String subName;

    public EduSubject() {
    }
    public EduSubject(Long id) {
        this.id=id;
    }

    public EduSubject(String subName) {
        this.subName = subName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }
}
