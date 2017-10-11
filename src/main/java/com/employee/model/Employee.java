package com.employee.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Objects;

public class Employee {
    private String name;
    private String designation;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date joiningDate;

    public Employee() {
    }

    public Employee(String name, String designation, Date joiningDate) {
        this.name = name;
        this.designation = designation;
        this.joiningDate = joiningDate;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public static NameBuilder newPerson(){
        return name -> designation -> joiningDate -> new Employee(name,designation, joiningDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    //Java 8 Builder
    interface NameBuilder {
        DesignationBuilder name(String name);
    }
    interface DesignationBuilder {
        JoiningDateBuilder designation(String designation);
    }
    interface JoiningDateBuilder {
        Employee joiningDate(Date joiningDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (designation != null ? !designation.equals(employee.designation) : employee.designation != null)
            return false;
        return joiningDate != null ? joiningDate.equals(employee.joiningDate) : employee.joiningDate == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, designation, joiningDate);
    }

}
