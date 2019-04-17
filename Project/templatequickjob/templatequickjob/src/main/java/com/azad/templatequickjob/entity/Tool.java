package com.azad.templatequickjob.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotEmpty(message = "Enter Tool Name")
    @Size(min = 2,max = 50,message = "Tool name must be between 2 and 50 Characters")
    private String toolName;
    @Column(nullable = false)
    private String experienceLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Tool() {
    }

    public Tool(@NotEmpty(message = "Enter Tool Name") @Size(min = 2, max = 50, message = "Tool name must be between 2 and 50 Characters") String toolName, String experienceLevel, User user) {
        this.toolName = toolName;
        this.experienceLevel = experienceLevel;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "id=" + id +
                ", toolName='" + toolName + '\'' +
                ", experienceLevel='" + experienceLevel + '\'' +
                ", user=" + user +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
