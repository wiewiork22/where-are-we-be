package com.employeedashboard.oirs.dto;

public record EmployeeRequestDTO(
        String fullName,
        String email,
        String position,
        String squad,
        String department,
        AddressDTO address
) {

    public EmployeeRequestDTO {
        if (fullName == null || fullName.isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be null or empty");
        }

        String[] nameParts = fullName.split(" ");

        if (nameParts.length < 2) {
            throw new IllegalArgumentException("Full name must contain exactly two parts: first name and last name");
        }

        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$") || email.isEmpty()) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (position == null|| position.isEmpty()) {
            throw new IllegalArgumentException("Position cannot be null");
        }

        if (squad == null || squad.isEmpty()) {
            throw new IllegalArgumentException("Squad cannot be null");
        }

        if (department == null || department.isEmpty()){
            throw new IllegalArgumentException("Department cannot be null");
        }

    }
}
