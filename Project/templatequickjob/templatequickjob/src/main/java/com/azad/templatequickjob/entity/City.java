package com.azad.templatequickjob.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    @Size(min = 2,max = 30,message = "City name must be between 2 and 30 Characters")
    private String cityName;
    //@Column(nullable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "division_id")
    private Distric distric;

    public City() {
    }
    public City(Long id) {
        this.id=id;
    }

    public City(String cityName) {
        this.cityName = cityName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Distric getDistric() {
        return distric;
    }

    public void setDistric(Distric distric) {
        this.distric = distric;
    }

    public City(@Size(min = 2, max = 30, message = "City name must be between 2 and 30 Characters") String cityName, Distric distric) {
        this.cityName = cityName;
        this.distric = distric;
    }
}
