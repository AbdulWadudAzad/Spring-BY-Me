package com.coderbd.springdifferentlayers.dto;
//Data Transfer Object
public class StudentDTOReport {


    private long noOfStudents;
    private String email;

    public StudentDTOReport() {
    }

    public StudentDTOReport(long noOfStudents, String email) {
        this.noOfStudents = noOfStudents;
        this.email = email;
    }

    public long getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(int noOfStudents) {
        this.noOfStudents = noOfStudents;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
