package com.azad.dtoreportmaking.dto;

public class StudentDTOReport {
    private Long noOfStudent;
    private String email;

    public StudentDTOReport() {
    }

    public StudentDTOReport(Long noOfStudent, String email) {
        this.noOfStudent = noOfStudent;
        this.email = email;
    }

    public Long getNoOfStudent() {
        return noOfStudent;
    }

    public void setNoOfStudent(Long noOfStudent) {
        this.noOfStudent = noOfStudent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
