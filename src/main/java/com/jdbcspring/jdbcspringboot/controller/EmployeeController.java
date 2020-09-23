package com.jdbcspring.jdbcspringboot.controller;

import com.jdbcspring.jdbcspringboot.dao.EmployeeDao;
import com.jdbcspring.jdbcspringboot.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;
    @PostMapping(value="/save")
    public String saveEmp(@RequestBody Employee employee){
        return employeeDao.saveEmployee(employee);
    }
    @GetMapping(value="/getemplist")
    public List<Employee> getEmplist(){
        return employeeDao.getList();
    }

    @GetMapping(value="/getemp/{id}")
    public Employee getEmp(@PathVariable Integer id){
        return employeeDao.getEmp(id);
    }

    @GetMapping(value="/getdata")
    public List<Map<String,Object>> getData(){
        return employeeDao.getEmpDeptList();
    }
}
