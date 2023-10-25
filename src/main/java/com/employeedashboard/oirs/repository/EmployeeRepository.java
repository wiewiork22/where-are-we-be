package com.employeedashboard.oirs.repository;

import com.employeedashboard.oirs.domain.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface EmployeeRepository {

    @Select("SELECT * FROM employee_table")
    List<Employee> getAll();

    @Select("SELECT * FROM employee_table WHERE id=#{id}")
    Optional<Employee> getById(int id);

    @Insert("INSERT INTO employee_table (name, position, squad, department, address)"
            + "VALUES (#{name}, #{position}, #{squad}, #{department}, #{address})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void add(Employee employee);

    @Transactional
    @Update("UPDATE employee_table SET name = #{name}, position = #{position}, squad = #{squad}, department = #{department}, address = #{address} WHERE id = #{id}")
    boolean updateEmployee(Employee employee);
}
