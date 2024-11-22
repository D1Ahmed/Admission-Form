package com.example.stepbystepnow;

public class Person {
    private final String name;
    private final String fatherName;
    private final String cnic;
    private final String dob;
    private final String gender;
    private final String city;

    public Person(String name, String fatherName, String cnic, String dob, String gender, String city) {
        this.name = name;
        this.fatherName = fatherName;
        this.cnic = cnic;
        this.dob = dob;
        this.gender = gender;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", cnic='" + cnic + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}