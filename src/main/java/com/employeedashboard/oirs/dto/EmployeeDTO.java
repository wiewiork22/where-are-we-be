package com.employeedashboard.oirs.dto;

public record EmployeeDTO(
        int id,
        String name,
        String email,
        String position,
        String squad,
        String department,
        int addressId
) {
}
