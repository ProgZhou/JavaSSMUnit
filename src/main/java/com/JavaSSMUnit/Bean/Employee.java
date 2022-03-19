package com.JavaSSMUnit.Bean;

public class Employee {
    private Integer id;

    private String empName;

    private String gender;

    private String email;

    private Integer department;

    private Department dept;

    public Employee() {
    }

    public Employee(Integer id, String empName, String gender, String email, Integer department) {
        this.id = id;
        this.empName = empName;
        this.gender = gender;
        this.email = email;
        this.department = department;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empName='" + empName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", department=" + department +
                ", dept=" + dept +
                '}';
    }
}