package com.employeedashboard.oirs.repository;

import com.employeedashboard.oirs.domain.ImageFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DocumentRepository {

	@Insert("INSERT INTO image_file (employee_id, image_data) VALUES (#{employeeId}, #{imageData, jdbcType=BLOB})")
	void insertImage(ImageFile image);

}
