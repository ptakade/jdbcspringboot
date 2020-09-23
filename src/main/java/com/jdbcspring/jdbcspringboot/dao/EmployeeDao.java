package com.jdbcspring.jdbcspringboot.dao;

import com.jdbcspring.jdbcspringboot.model.Employee;
import com.jdbcspring.jdbcspringboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDao implements EmployeeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String saveEmployee(Employee employee){
        String sql="insert into employee values(?,?,?)";
        jdbcTemplate.update(sql, new Object[]{
                employee.getId(),employee.getName(),employee.getCity()

        });
        return "Employee saved";
    }

    @Override
    public List<Employee> getList() {
        String sql="select * from employee";
        List<Employee> list=jdbcTemplate.query(sql,
                new BeanPropertyRowMapper(Employee.class));
        return list;
    }

    @Override
    public Employee getEmp(Integer id) {
        System.out.println("In GetEmp Method");
        String sql="select * from employee where id=?";
        Employee e= (Employee) jdbcTemplate.queryForObject(sql,new Object[]{id},
                new BeanPropertyRowMapper(Employee.class));
        return e;
    }

    @Override
    public List<Map<String, Object>> getEmpDeptList() {
        String sql="select a.id,a.name,a.city,b.name as deptName from employee a,department b where a.dept_id=b.id";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
        return list;
    }
}
