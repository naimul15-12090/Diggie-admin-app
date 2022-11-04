package com.example.diggieadmin.faculty;

public class TeacherData {
    private String name, email, initial, image, phone,teacherId, key;

    public TeacherData() {
    }

    public TeacherData(String name, String email, String initial, String image, String phone, String teacherId, String key) {
        this.name = name;
        this.email = email;
        this.initial = initial;
        this.image = image;
        this.phone = phone;
        this.teacherId = teacherId;
        this.key = key;
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

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
