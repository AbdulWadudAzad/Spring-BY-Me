package com.azad.templatequickjob.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    @NotEmpty(message = "Project Name")
    @Size(min = 2,max = 100,message = "Project name must be between 2 and 100 Characters")
    private String projectName;

    @Column(nullable = true)
    @Size(min = 2,max = 1000,message = "Description must be between 2 and 1000 Characters")
    private String description;

    @Column(nullable = true)
    private String technologyUsed;

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

    @Column(nullable = true)
    private String language;

    @Column(nullable = true)
    private String framework;

    @Column(nullable = true)
    private String liveUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Project() {
    }
//    public Project(Long id) {
////        this.id=id;
////    }
////
////    public Project(String projectName) {
////        this.projectName = projectName;
////    }
////


    public Project(@NotEmpty(message = "Project Name") @Size(min = 2, max = 100, message = "Project name must be between 2 and 100 Characters") String projectName, @Size(min = 2, max = 200, message = "Description must be between 2 and 200 Characters") String description, String technologyUsed, String duration, Date startDate, Date endDate, String language, String framework, String liveUrl, User user) {
        this.projectName = projectName;
        this.description = description;
        this.technologyUsed = technologyUsed;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.language = language;
        this.framework = framework;
        this.liveUrl = liveUrl;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", description='" + description + '\'' +
                ", technologyUsed='" + technologyUsed + '\'' +
                ", duration='" + duration + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", language='" + language + '\'' +
                ", framework='" + framework + '\'' +
                ", liveUrl='" + liveUrl + '\'' +
                ", user=" + user +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnologyUsed() {
        return technologyUsed;
    }

    public void setTechnologyUsed(String technologyUsed) {
        this.technologyUsed = technologyUsed;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFramework() {
        return framework;
    }

    public void setFramework(String framework) {
        this.framework = framework;
    }

    public String getLiveUrl() {
        return liveUrl;
    }

    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
