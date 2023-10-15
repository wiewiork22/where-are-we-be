package com.employeedashboard.oirs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employees {
    public int id;
    public String name;
    public String position;
    public String squad;
    public String department;
    public String address;
}
