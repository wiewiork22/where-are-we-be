package com.employeedashboard.oirs.dto;

public record AddressDTO(String street, String city, String state, String postCode, String country, Double lat,
        Double lng) {
}
