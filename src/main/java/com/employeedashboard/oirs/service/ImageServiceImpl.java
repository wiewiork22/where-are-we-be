package com.employeedashboard.oirs.service;

import com.employeedashboard.oirs.domain.ImageFile;
import com.employeedashboard.oirs.repository.DocumentRepository;
import com.sun.jdi.InternalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

	private final DocumentRepository documentRepository;

	@Override
	public void saveImage(int employeeId, MultipartFile file) {
		if (!validateImageType(file)) {
			throw new IllegalArgumentException("File must be a JPEG or PNG image.");
		}

		if (!validateImageSize(file)) {
			throw new IllegalArgumentException("File size must not exceed 5 MB and empty.");
		}

		try {
			byte[] imageData = file.getBytes();

			ImageFile imageFile = new ImageFile(employeeId, imageData);

			documentRepository.insertImage(imageFile);
		} catch (Exception e) {
			throw new InternalException("Image upload failed");
		}
	}

	private boolean validateImageSize(MultipartFile file) {
		long maxSize = 5 * 1024 * 1024;

		return file.getSize() != 0 && file.getSize() < maxSize;
	}

	private boolean validateImageType(MultipartFile file) {
		String contentType = file.getContentType();

		return "image/jpeg".equals(contentType) || "image/png".equals(contentType);
	}
}
