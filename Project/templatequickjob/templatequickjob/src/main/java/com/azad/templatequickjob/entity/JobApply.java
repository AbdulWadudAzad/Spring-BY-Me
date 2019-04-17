package com.azad.templatequickjob.entity;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class JobApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applicationDate = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jobpost_id")
    private JobPost jobPostId;

    public JobApply() {
    }

    public JobApply(Date lastModifiedDate, User userId, JobPost jobPostId) {
        this.applicationDate = lastModifiedDate;
        this.userId = userId;
        this.jobPostId = jobPostId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLastModifiedDate() {
        return applicationDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.applicationDate = lastModifiedDate;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public JobPost getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(JobPost jobPostId) {
        this.jobPostId = jobPostId;
    }

    @Override
    public String toString() {
        return "JobApply{" +
                "id=" + id +
                ", lastModifiedDate=" + applicationDate +
                ", userId=" + userId +
                ", jobPostId=" + jobPostId +
                '}';
    }
}
