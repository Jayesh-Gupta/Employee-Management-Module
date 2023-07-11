package com.example.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Department implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "departmentName")
    private String departmentName;

    public Department(){

    }
    public Department(int id, String departmentName) {
        this.id = id;
        this.departmentName=departmentName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public int getId() {
        return id;
    }
}
