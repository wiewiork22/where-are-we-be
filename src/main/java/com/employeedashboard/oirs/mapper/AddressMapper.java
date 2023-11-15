package com.employeedashboard.oirs.mapper;

import com.employeedashboard.oirs.domain.Address;
import com.employeedashboard.oirs.dto.AddressDTO;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
	public AddressDTO mapToAddressDTO(Address address) {
		return new AddressDTO(address.getStreet(), address.getCity(), address.getState(), address.getPostCode(),
				address.getCountry(), address.getLat(), address.getLng());
	}
}
