package com.company.init.DTO;

import java.util.Objects;

public class UserDTO {
    private int id;
    private String username;
    private String email;
    private String department;
    private String role;
    private int salary;

    // Default constructor
    public UserDTO() {
    }

    // Constructor with all fields
    public UserDTO(int id, String username, String email, String department, String role, int salary) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.department = department;
        this.role = role;
        this.salary = salary;
    }

    // Getters and setters for all fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
