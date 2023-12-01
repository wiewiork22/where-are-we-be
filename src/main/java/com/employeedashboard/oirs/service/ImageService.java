package com.employeedashboard.oirs.service;

import com.employeedashboard.oirs.domain.ImageFile;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	void saveImage(int employeeId, MultipartFile file);

	ImageFile getImageByEmployeeEmail(String employeeEmail);
}
