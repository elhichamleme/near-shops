package com.unitedremote.nearshops.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "shops")
public class Shop {

    @Id
    public String id;
    private String picture;
    private String name;
    private String email;
    private String city;
    private Location location;
    private double distance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object obj) {

        return this.id.equals(((Shop)obj).getId());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
