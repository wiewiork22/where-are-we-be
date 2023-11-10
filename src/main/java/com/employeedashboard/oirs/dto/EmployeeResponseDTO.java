package com.employeedashboard.oirs.dto;

public record EmployeeResponseDTO(int id, String fullName, String position, String squad, String department,
        AddressDTO address) {
}
