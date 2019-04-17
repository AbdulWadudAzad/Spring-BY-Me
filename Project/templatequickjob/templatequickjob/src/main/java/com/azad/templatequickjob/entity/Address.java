package com.azad.templatequickjob.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    @Size(min = 2,max = 40,message = "Home name must be between 2 and 40 Characters")
    private String home;
    @Column(nullable = true)
    @Size(min = 2,max = 30,message = "Road name must be between 2 and 30 Characters")
    private String road;

//    @Column(nullable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;
//    @Column(nullable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "division_id")
    private Division division;
//    @Column(nullable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distric_id")
    private Distric distric;
//    @Column(nullable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Address() {
    }

    public Address(@Size(min = 2, max = 40, message = "Home name must be between 2 and 40 Characters") String home, @Size(min = 2, max = 30, message = "Road name must be between 2 and 30 Characters") String road, Country country, Division division, Distric distric, City city, User user) {
        this.home = home;
        this.road = road;
        this.country = country;
        this.division = division;
        this.distric = distric;
        this.city = city;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", home='" + home + '\'' +
                ", road='" + road + '\'' +
                ", country=" + country +
                ", division=" + division +
                ", distric=" + distric +
                ", city=" + city +
                ", user=" + user +
                '}';
    }
}
