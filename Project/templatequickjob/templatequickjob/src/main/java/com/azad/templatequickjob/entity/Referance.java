package com.azad.templatequickjob.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
public class Referance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    @Size(min = 2,max = 30,message = "Name must be between 2 and 30 Characters")
    private String name;
    @Column(nullable = true)
    @Size(min = 2,max = 40,message = "designation must be between 2 and 40 Characters")
    private String designation;
    @Column(nullable = true)
    @Size(min = 2,max = 50,message = "Institution must be between 2 and 50 Characters")
    private String institution;
    @Email
    @Column(nullable = true)
    private String email;
    @Column(nullable = true)
    private String mobile;
    @Column(nullable = true)
    private String website;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Referance() {
    }

    public Referance(@Size(min = 2, max = 30, message = "Name must be between 2 and 30 Characters") String name, @Size(min = 2, max = 40, message = "designation must be between 2 and 40 Characters") String designation, @Size(min = 2, max = 50, message = "Institution must be between 2 and 50 Characters") String institution, @Email String email, String mobile, String website, User user) {
        this.name = name;
        this.designation = designation;
        this.institution = institution;
        this.email = email;
        this.mobile = mobile;
        this.website = website;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Referance{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", institution='" + institution + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", website='" + website + '\'' +
                ", user=" + user +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
