package com.employeedashboard.oirs.dto;

public record EmployeeResponseDTO(int id, String fullName, String email, String position, String squad, String department,
        AddressDTO address) {
}
