package com.employeedashboard.oirs.repository;

import com.employeedashboard.oirs.domain.Address;
import java.util.Optional;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Mapper
public interface AddressRepository {
	@Insert("INSERT INTO address (street_address, city, state, postcode, country) VALUES (#{street}, #{city}, #{state}, #{postCode}, #{country})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void save(Address address);

	@Transactional
	@Update("UPDATE address SET street_address = #{street}, city = #{city}, state = #{state}, postcode = #{postCode}, country = #{country} WHERE id = #{id}")
	boolean updateAddress(Address address);

	@Select("Select * FROM address a WHERE id = #{addressId}")
	Optional<Address> getById(Integer addressId);

	@Delete("Delete FROM address WHERE id = #{addressId}")
	void deleteById(Integer addressId);
}
