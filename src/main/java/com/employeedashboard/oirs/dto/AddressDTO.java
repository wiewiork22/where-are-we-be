package com.employeedashboard.oirs.dto;

public record AddressDTO(String street, String city, String state, String postCode, String country, Double lat,
                         Double lng) {

    public AddressDTO {
        if (street == null || street.isEmpty()) {
            throw new IllegalArgumentException("Street cannot be null or empty");
        }

        if (city == null || city.isEmpty()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }

        if (state == null || state.isEmpty()) {
            throw new IllegalArgumentException("State cannot be null or empty");
        }

        if (postCode == null || postCode.isEmpty()) {
            throw new IllegalArgumentException("Post code cannot be null or empty");
        }

        if (country == null || country.isEmpty()) {
            throw new IllegalArgumentException("Country cannot be null or empty");
        }

        if (lat == null ){
            throw new IllegalArgumentException("Latitude cannot be null");
        }

        if (lng == null) {
            throw new IllegalArgumentException("Longitude cannot be null");
        }

        if (lat.isNaN() || lat.isInfinite()) {
            throw new IllegalArgumentException("Latitude must be a valid floating-point number");
        }

        if (lng.isNaN() || lng.isInfinite()) {
            throw new IllegalArgumentException("Longitude must be a valid floating-point number");
        }
    }
}
