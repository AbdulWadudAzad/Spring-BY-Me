package com.azad.templatequickjob.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    @Size(min = 2,max = 80,message = "Certification name must be between 2 and 80 Characters")
    private String certificName;

    @Column(nullable = true)
    @Size(min = 2,max = 80,message = "Organization name must be between 2 and 80 Characters")
    private String organization;

    @Column(nullable = true)
   private String duration;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = true)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = true)
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Certification() {
    }

    public Certification(@Size(min = 2, max = 80, message = "Certification name must be between 2 and 80 Characters") String certificName, @Size(min = 2, max = 80, message = "Organization name must be between 2 and 80 Characters") String organization, String duration, Date startDate, Date endDate, User user) {
        this.certificName = certificName;
        this.organization = organization;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Certification{" +
                "id=" + id +
                ", certificName='" + certificName + '\'' +
                ", organization='" + organization + '\'' +
                ", duration='" + duration + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", user=" + user +
                '}';
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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
