package org.example.laba_10.dao;

public class PeopleClass {
    private int classId;
    private String className;
    private String classDay;
    private String classHours;

    public PeopleClass() { }

    public PeopleClass(int classId, String className, String classDay, String classHours) {
        this.classId = classId;
        this.className = className;
        this.classDay = classDay;
        this.classHours = classHours;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDay() {
        return classDay;
    }

    public void setClassDay(String classDay) {
        this.classDay = classDay;
    }

    public String getClassHours() {
        return classHours;
    }

    public void setClassHours(String classHours) {
        this.classHours = classHours;
    }
}
