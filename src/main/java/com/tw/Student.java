package com.tw;


import java.util.Map;

public class Student {
    String name;
    String number;
    Map<String,Double> course;

    public Student(String name, String number, Map<String, Double> course) {
        this.name = name;
        this.number = number;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public Map<String, Double> getCourse() {
        return course;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCourse(Map<String, Double> course) {
        this.course = course;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return course.equals(student.course)&&name.equals(name)&&number.equals(student.number);
    }


}
