package com.employeedashboard.oirs.databaseInitializer;

import com.employeedashboard.oirs.service.EmployeeServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final EmployeeServiceImpl employeeServiceImpl;

    public DatabaseInitializer(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @Override
    public void run(String... args) {
        employeeServiceImpl.addDefaultUsers();
    }

}
