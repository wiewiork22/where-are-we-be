package com.employeedashboard.oirs.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	private int id;
	private String street;
	private String city;
	private String state;
	private String postCode;
	private String country;

}
