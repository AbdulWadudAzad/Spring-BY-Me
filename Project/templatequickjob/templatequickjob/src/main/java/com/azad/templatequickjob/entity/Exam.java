package com.azad.templatequickjob.entity;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private int year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eduLevel_id")
    private EduLevel eduLevel;

   // @Column(nullable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private EduSubject eduSubject;

    //@Column(nullable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id")
    private Result result;

    //Column(nullable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Exam() {
    }

    public Exam(int year, EduLevel eduLevel, EduSubject eduSubject, Result result, Board board, User user) {
        this.year = year;
        this.eduLevel = eduLevel;
        this.eduSubject = eduSubject;
        this.result = result;
        this.board = board;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public EduLevel getEduLevel() {
        return eduLevel;
    }

    public void setEduLevel(EduLevel eduLevel) {
        this.eduLevel = eduLevel;
    }

    public EduSubject getEduSubject() {
        return eduSubject;
    }

    public void setEduSubject(EduSubject eduSubject) {
        this.eduSubject = eduSubject;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", year=" + year +
                ", eduLevel=" + eduLevel +
                ", eduSubject=" + eduSubject +
                ", result=" + result +
                ", board=" + board +
                ", user=" + user +
                '}';
    }
}
