package com.employeedashboard.oirs.dto;

public record EmployeeResponseDTO(
     int id,
     String name,
     String position,
     String squad,
     String department,
     String address
){}
