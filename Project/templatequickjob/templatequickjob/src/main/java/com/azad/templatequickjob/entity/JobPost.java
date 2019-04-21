package com.azad.templatequickjob.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private String companyName;

    @Column(nullable = true)
    @Size(min = 1, max = 2000, message = "Hey, Size must be between 1 and 2000 character")
    private String jobDescription;

    @Column(nullable = true)
    private String fullAddress;

    @Column(nullable = true)
    private String vacancy;

    @Column(nullable = true)
    private String contactEmail;

//    @Column(nullable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jobTitle_id")
    private JobTitle jobTitle;
//    @Column(nullable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empType_id")
    private EmploymentType employmentType;
//    @Column(nullable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jobCat_id")
    private JobCategory jobCategory;
//    @Column(nullable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seLevel_id")
    private SeniorityLevel seniorityLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyInd_id")
    private CompanyIndustry companyIndustry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "division_id")
    private Division division;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distric_id")
    private Distric distric;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "yearExpe_id")
    private YearExperience yearExperience;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eduLevel_id")
    private EduLevel eduLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certification_id")
    private Certification certification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public JobPost() {
    }

    public JobPost(String companyName, @Size(min = 1, max = 2000, message = "Hey, Size must be between 1 and 2000 character") String jobDescription, String fullAddress, String vacancy, String contactEmail, JobTitle jobTitle, EmploymentType employmentType, JobCategory jobCategory, SeniorityLevel seniorityLevel, CompanyIndustry companyIndustry, Country country, Division division, Distric distric, Skill skill, YearExperience yearExperience, Language language, EduLevel eduLevel, Certification certification, User user) {
        this.companyName = companyName;
        this.jobDescription = jobDescription;
        this.fullAddress = fullAddress;
        this.vacancy = vacancy;
        this.contactEmail = contactEmail;
        this.jobTitle = jobTitle;
        this.employmentType = employmentType;
        this.jobCategory = jobCategory;
        this.seniorityLevel = seniorityLevel;
        this.companyIndustry = companyIndustry;
        this.country = country;
        this.division = division;
        this.distric = distric;
        this.skill = skill;
        this.yearExperience = yearExperience;
        this.language = language;
        this.eduLevel = eduLevel;
        this.certification = certification;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public JobCategory getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(JobCategory jobCategory) {
        this.jobCategory = jobCategory;
    }

    public SeniorityLevel getSeniorityLevel() {
        return seniorityLevel;
    }

    public void setSeniorityLevel(SeniorityLevel seniorityLevel) {
        this.seniorityLevel = seniorityLevel;
    }

    public CompanyIndustry getCompanyIndustry() {
        return companyIndustry;
    }

    public void setCompanyIndustry(CompanyIndustry companyIndustry) {
        this.companyIndustry = companyIndustry;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Distric getDistric() {
        return distric;
    }

    public void setDistric(Distric distric) {
        this.distric = distric;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public YearExperience getYearExperience() {
        return yearExperience;
    }

    public void setYearExperience(YearExperience yearExperience) {
        this.yearExperience = yearExperience;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public EduLevel getEduLevel() {
        return eduLevel;
    }

    public void setEduLevel(EduLevel eduLevel) {
        this.eduLevel = eduLevel;
    }

    public Certification getCertification() {
        return certification;
    }

    public void setCertification(Certification certification) {
        this.certification = certification;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public String toString() {
        return "JobPost{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", fullAddress='" + fullAddress + '\'' +
                ", vacancy='" + vacancy + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", jobTitle=" + jobTitle +
                ", employmentType=" + employmentType +
                ", jobCategory=" + jobCategory +
                ", seniorityLevel=" + seniorityLevel +
                ", companyIndustry=" + companyIndustry +
                ", country=" + country +
                ", division=" + division +
                ", distric=" + distric +
                ", skill=" + skill +
                ", yearExperience=" + yearExperience +
                ", language=" + language +
                ", eduLevel=" + eduLevel +
                ", certification=" + certification +
                ", user=" + user +
                '}';
    }
}
