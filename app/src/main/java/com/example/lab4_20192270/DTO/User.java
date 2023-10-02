package com.example.lab4_20192270.DTO;

public class User {
    private NombreUser name;
    private String gender;
    private Location location;
    private String email;
    private String phone;
    private ImageProfile picture;
    public NombreUser getName() {
        return name;
    }

    public void setName(NombreUser name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ImageProfile getPicture() {
        return picture;
    }

    public void setPicture(ImageProfile picture) {
        this.picture = picture;
    }
}
