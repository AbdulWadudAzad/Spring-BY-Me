package com.azad;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "apps")
public class Product {
    private int id;
    private String name;
    private Long price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
