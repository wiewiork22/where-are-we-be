package com.employeedashboard.oirs.repository;

import com.employeedashboard.oirs.domain.Employees;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EmployeeRepository {

    @Select("SELECT * FROM employee_table")
    List<Employees> getAll();

    @Select("SELECT * FROM employee_table WHERE id=#{id}")
    Employees getById(int id);
}
