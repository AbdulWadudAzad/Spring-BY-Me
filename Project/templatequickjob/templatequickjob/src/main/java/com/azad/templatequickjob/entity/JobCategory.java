package com.azad.templatequickjob.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class JobCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    @Size(min = 2,max = 80,message = "Job title must be between 2 and 50 Characters")
    private String jobCategoryName;

    public JobCategory() {
    }

    public JobCategory(@Size(min = 2, max = 80, message = "Job title must be between 2 and 50 Characters") String jobCategoryName) {
        this.jobCategoryName = jobCategoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobCategoryName() {
        return jobCategoryName;
    }

    public void setJobCategoryName(String jobCategoryName) {
        this.jobCategoryName = jobCategoryName;
    }
}
