package com.azad.templatequickjob.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    @Size(min = 2,max = 40,message = "Country name must be between 2 and 40 Characters")
    private String countryName;
    @Column(nullable = true)
    @Size(min = 2,max = 15,message = "Country Code must be between 2 and 15 Characters")
    private String countryCode;

    public Country() {
    }


    public Country(@Size(min = 2, max = 40, message = "Country name must be between 2 and 40 Characters") String countryName, @Size(min = 2, max = 15, message = "Country Code must be between 2 and 15 Characters") String countryCode) {
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
