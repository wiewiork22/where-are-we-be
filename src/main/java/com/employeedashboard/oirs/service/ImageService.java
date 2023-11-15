package com.employeedashboard.oirs.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	void saveImage(int employeeId, MultipartFile file);

}
