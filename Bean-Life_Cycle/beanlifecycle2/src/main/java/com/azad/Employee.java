package com.azad;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//Its not a good approch of bean life cycle
public class Employee implements InitializingBean, DisposableBean {
    private int id;
    private String position;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", position='" + position + '\'' +
                '}';
    }

    public void destroy() throws Exception {
        System.out.println("I am in destroy... ");

    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("I am in afterPropertiesSet... ");

    }

}
