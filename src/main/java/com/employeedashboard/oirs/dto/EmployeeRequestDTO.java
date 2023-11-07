package com.employeedashboard.oirs.dto;

public record EmployeeRequestDTO(
        String fullName,
        String email,
        String position,
        String squad,
        String department,
        AddressDTO address
) {
}
