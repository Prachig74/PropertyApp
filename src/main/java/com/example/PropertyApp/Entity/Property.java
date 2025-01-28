package com.example.PropertyApp.Entity;

import jakarta.persistence.*;

import java.util.Date;

//@Entity
//@Table(name = "property")
//public class Property {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    private String location;
//    private Double price;
//    private Double area;
//    private Date availableDate;
//    private String name;
//    private Integer bhkCount;
//
//    // Getter and Setter for id
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    // Getter and Setter for user
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    // Getter and Setter for location
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    // Getter and Setter for price
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    // Getter and Setter for area
//    public Double getArea() {
//        return area;
//    }
//
//    public void setArea(Double area) {
//        this.area = area;
//    }
//
//    // Getter and Setter for availableDate
//    public Date getAvailableDate() {
//        return availableDate;
//    }
//
//    public void setAvailableDate(Date availableDate) {
//        this.availableDate = availableDate;
//    }
//
//    // Getter and Setter for name
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    // Getter and Setter for bhkCount
//    public Integer getBhkCount() {
//        return bhkCount;
//    }
//
//    public void setBhkCount(Integer bhkCount) {
//        this.bhkCount = bhkCount;
//    }
//}


@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private double price;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
