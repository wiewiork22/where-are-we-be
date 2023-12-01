package com.employeedashboard.oirs.repository;

import com.employeedashboard.oirs.domain.ImageFile;
import java.util.Optional;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DocumentRepository {

	@Insert("INSERT INTO image_file (employee_id, image_data) VALUES (#{employeeId}, #{imageData, jdbcType=BLOB})")
	void insertImage(ImageFile image);

	@Select("SELECT * FROM image_file i INNER JOIN employee_table e ON e.email = #{employeeEmail} AND e.id = i.employee_id")
	Optional<ImageFile> getImageByEmployeeEmail(String employeeEmail);
}
