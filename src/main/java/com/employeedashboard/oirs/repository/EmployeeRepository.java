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

    @Select("SELECT * FROM employee_table WHERE email = #{email}")
    //    @Select("SELECT * FROM employee_table WHERE email = #{email} AND isDeleted = false") latter when we add delete feild
    @Results({
            @Result(property = "role", column = "user_role")
    })
    Optional<Employee> findByEmail(@Param("email") String email);

    @Insert("INSERT INTO employee_table (name, email, password, position, squad, department, address_Id, user_role) " +
            "VALUES (#{name}, #{email}, #{password}, #{position}, #{squad}, #{department}, #{addressId}, #{role})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(Employee employee);

    @Transactional
    @Update("UPDATE employee_table SET name = #{name}, position = #{position}, squad = #{squad}, department = #{department}, address_id = #{addressId} WHERE id = #{id}")
    boolean updateEmployee(Employee employee);

    @Select("SELECT address_id FROM employee_table WHERE id = #{employeeId}")
    int getAddresId(int employeeId);
}
