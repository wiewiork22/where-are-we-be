package com.employeedashboard.oirs.domain;

public record ImageFile(
        int employeeId,
        byte[] imageData
) { }
