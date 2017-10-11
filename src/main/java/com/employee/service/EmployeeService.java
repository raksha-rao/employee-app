package com.employee.service;

import com.employee.factory.PersonFactory;
import com.employee.model.Employee;
import com.employee.model.EmployeePredicates;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    public void sortEmployee(List<Employee> employeeList) {
        //Java 8 - lambda expression
        employeeList.sort((a, b) -> b.getName().compareTo(a.getName()));
    }

    public Employee populateEmployees(String name, String designation, String dateInString) throws ParseException {
        //Java 8 - constructor references
        PersonFactory<Employee> employeeFactory = Employee::new;
        SimpleDateFormat formatter = new SimpleDateFormat(dateInString);
        Date date = formatter.parse(dateInString);
        return employeeFactory.create(name, designation, date);
    }


    public List<Employee> readFromFile(String employeesFile) throws IOException, ParseException {
        CSVReader reader = null;
        List<Employee> employeeList = new ArrayList<>();
        reader = new CSVReader(new FileReader(employeesFile));
        String[] line;
        while ((line = reader.readNext()) != null) {
            Employee employee = populateEmployees(line[0], line[1], line[2]);
            employeeList.add(employee);
        }
        return employeeList;
    }

    public Employee searchEmployee(String employeeName, List<Employee> employeeList) {
        // Java 8 - streams
        return employeeList.stream()
                .filter(employee -> employee.getName().equals(employeeName))
                .findFirst()
                .orElse(null);
    }

    public boolean validateEmployee(Employee employee) {
       // boolean isValid = EmployeePredicates.isNotEmpty.test(employee.getName()) && EmployeePredicates.isValidLength.test(employee.getName());

        Optional<String> optional = Optional.of(employee.getName());
        boolean isValid = optional.isPresent();

        isValid = isValid && EmployeePredicates.isNotEmpty.test(employee.getDesignation());

        return isValid;
    }

    public Map<String, Employee> populateEmployeeMap(List<Employee> employeeList){
        // Java 8 - Maps
       Map<String,Employee> employeeMap = employeeList
                .stream()
                .collect(Collectors.toMap(Employee::getName, e -> e));
       return employeeMap;
    }
}
