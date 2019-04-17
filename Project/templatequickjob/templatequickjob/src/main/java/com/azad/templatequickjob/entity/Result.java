package com.azad.templatequickjob.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    @Size(min = 1,max = 40,message = "Result name must be between 2 and 40 Characters")
    private String resultName;

    public Result() {
    }
    public Result(Long id) {
        this.id=id;
    }

    public Result(String resultName) {
        this.resultName = resultName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }
}
