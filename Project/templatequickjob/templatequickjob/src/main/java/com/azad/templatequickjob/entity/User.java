package com.azad.templatequickjob.entity;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotEmpty(message = "Enter First Name")
    @Size(min = 1, max = 50, message = "Hey, Size must be between 1 and 50")
    @Column(nullable = false)
    private String firstName;

    private String lastName;


    @Column(nullable = false, name = "user_name", unique = true)
    private String userName;

    @Column(nullable = true)
    private String password;

    @Email
    @NotEmpty(message = "Enter An Email")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String mobile;


    private String gender;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regiDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifiedDate = new Date();

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date birthDate;

    //////Token Activation ==============
    private boolean enabled;
    @Column(nullable = false)
    private String confirmationToken;

    //////File Upload==============
    @Column(nullable = true)
    private long fileSize;
    private String fileName;
    //  @Lob
    // private byte[] file;
    private String filePath;
    private String fileExtension;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;



//  Start Two-Way binding for show all data of these entity in cv
    @OneToMany(mappedBy = "user")
    private List<WorkExperience> workExperiences;

    @OneToMany(mappedBy = "user")
    private List<Language> languages;

    @OneToMany(mappedBy = "user")
    private List<Certification> certifications;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    @OneToMany(mappedBy = "user")
    private List<Exam> exams;

    @OneToMany(mappedBy = "user")
    private List<Project> projects;

    @OneToMany(mappedBy = "user")
    private List<Referance> referances;

    @OneToMany(mappedBy = "user")
    private List<Tool> tools;
    //  End Two-Way binding for show all data of these entity in cv

    public User() {
    }


    public User(@NotEmpty(message = "Enter First Name") @Size(min = 1, max = 50, message = "Hey, Size must be between 1 and 50") String firstName, String lastName, String userName, String password, @Email @NotEmpty(message = "Enter An Email") String email, String mobile, String gender, Date regiDate, Date lastModifiedDate, Date birthDate, boolean enabled, String confirmationToken, long fileSize, String fileName, String filePath, String fileExtension, Set<Role> roles, List<WorkExperience> workExperiences, List<Language> languages, List<Certification> certifications, List<Address> addresses, List<Exam> exams, List<Project> projects, List<Referance> referances, List<Tool> tools) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.regiDate = regiDate;
        this.lastModifiedDate = lastModifiedDate;
        this.birthDate = birthDate;
        this.enabled = enabled;
        this.confirmationToken = confirmationToken;
        this.fileSize = fileSize;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileExtension = fileExtension;
        this.roles = roles;
        this.workExperiences = workExperiences;
        this.languages = languages;
        this.certifications = certifications;
        this.addresses = addresses;
        this.exams = exams;
        this.projects = projects;
        this.referances = referances;
        this.tools = tools;
    }

    public User(User user) {
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.userName = user.userName;
        this.password = user.password;
        this.email = user.email;
        this.mobile = user.mobile;
        this.gender = user.gender;
        this.regiDate = user.regiDate;
        this.lastModifiedDate = user.lastModifiedDate;
        this.birthDate = user.birthDate;
        this.enabled = user.enabled;
        this.confirmationToken = user.confirmationToken;
        this.fileSize = user.fileSize;
        this.fileName = user.fileName;
        this.filePath = user.filePath;
        this.fileExtension = user.fileExtension;
        this.roles = user.roles;
        //this.designation = user.designation;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getRegiDate() {
        return regiDate;
    }

    public void setRegiDate(Date regiDate) {
        this.regiDate = regiDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(List<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Certification> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<Certification> certifications) {
        this.certifications = certifications;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Referance> getReferances() {
        return referances;
    }

    public void setReferances(List<Referance> referances) {
        this.referances = referances;
    }

    public List<Tool> getTools() {
        return tools;
    }

    public void setTools(List<Tool> tools) {
        this.tools = tools;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gender='" + gender + '\'' +
                ", regiDate=" + regiDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", birthDate=" + birthDate +
                ", enabled=" + enabled +
                ", confirmationToken='" + confirmationToken + '\'' +
                ", fileSize=" + fileSize +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                ", roles=" + roles +
                ", workExperiences=" + workExperiences +
                ", languages=" + languages +
                ", certifications=" + certifications +
                ", addresses=" + addresses +
                ", exams=" + exams +
                ", projects=" + projects +
                ", referances=" + referances +
                ", tools=" + tools +
                '}';
    }
}
