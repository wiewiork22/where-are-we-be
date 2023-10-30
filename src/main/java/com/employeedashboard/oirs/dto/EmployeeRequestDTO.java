package com.employeedashboard.oirs.dto;

public record EmployeeRequestDTO(
        String name,
        String email,
        String position,
        String squad,
        String department,
        String street,
        String city,
        String state,
        String postCode,
        String country
) {
}
