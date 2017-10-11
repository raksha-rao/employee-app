package com.employee.factory;

import com.employee.model.Employee;

import java.util.Date;

public interface PersonFactory<P extends Employee> {
    P create(String firstName, String designation, Date joiningDate);
}