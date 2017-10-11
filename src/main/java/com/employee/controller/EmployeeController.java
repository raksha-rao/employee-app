package com.employee.controller;

import com.employee.model.Employee;
import com.employee.response.BaseResponse;
import com.employee.response.ObjectResponse;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@RestController
@Path("/company/employees")
@Produces("application/json")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private String employeesFile = "./employees.csv";

    @GET
    public BaseResponse getAllEmployees(){
        BaseResponse response;
        try {

           List<Employee> employeeList = employeeService.readFromFile(employeesFile);
           employeeService.sortEmployee(employeeList);

           response = new ObjectResponse<Employee>(200, true,employeeList);
        } catch (Exception e) {
            response = new ObjectResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,false, e.getMessage());
        }
        return response;
    }

    @GET
    @Path("/{employeeName}")
    public BaseResponse searchEmployeeByName(@PathParam("employeeName") String employeeName) {
        BaseResponse response;
        try {
            List<Employee> employeeList = employeeService.readFromFile(employeesFile);
            Employee employee = employeeService.searchEmployee(employeeName,employeeList);
            response = new ObjectResponse<Employee>(200, true,employee);
        } catch (Exception e) {
            response = new ObjectResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,false, e.getMessage());
        }
        return response;
    }


    @GET
    @Path("/validate/{employeeName}")
    public BaseResponse validateEmployee(@PathParam("employeeName") String employeeName) {
        BaseResponse response;
        try {
            List<Employee> employeeList = employeeService.readFromFile(employeesFile);
            Employee employee = employeeService.searchEmployee(employeeName,employeeList);
            boolean isValid = employeeService.validateEmployee(employee);
            response = new ObjectResponse<Employee>(200, isValid);
        } catch (Exception e) {
            response = new ObjectResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,false, e.getMessage());
        }
        return response;
    }

}
