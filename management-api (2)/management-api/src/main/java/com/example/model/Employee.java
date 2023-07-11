package com.example.model;

import javax.persistence.*;

@Entity
public class Employee {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="first_name",nullable = false)
    private String firstName;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn private Department department;
    @Column(name="last_name")
    private String lastName;
    @Column(name="location")
    private String location;
    public Employee(){

    }
    public Employee(int id,String firstName,String lastName,String location,int deptId){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.location=location;
        this.department=new Department(deptId,"");
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
}
