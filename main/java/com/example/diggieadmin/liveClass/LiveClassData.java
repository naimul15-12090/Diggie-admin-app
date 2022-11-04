package com.example.diggieadmin.liveClass;

public class LiveClassData {
    private String courseCode, courseTitle, liveClassLink, classTime, key;

    public LiveClassData() {
    }



    public LiveClassData(String courseCode, String courseTitle, String liveClassLink, String classTime,  String key) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.liveClassLink = liveClassLink;
        this.classTime = classTime;
        this.key = key;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getLiveClassLink() {
        return liveClassLink;
    }

    public void setLiveClassLink(String liveClassLink) {
        this.liveClassLink = liveClassLink;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
