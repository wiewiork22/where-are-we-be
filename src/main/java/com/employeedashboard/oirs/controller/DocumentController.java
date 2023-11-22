package com.employeedashboard.oirs.controller;

import com.employeedashboard.oirs.auth.AuthenticationService;
import com.employeedashboard.oirs.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class DocumentController {

	private final AuthenticationService authenticationService;
	private final ImageService imageService;

	@PostMapping(value = "/image")
	public String uploadEmployeeImage(@RequestParam(name = "employeeId") Integer employeeId,
			@RequestParam(name = "file") MultipartFile file, @AuthenticationPrincipal UserDetails userDetails) {

		if (authenticationService.isAdmin(userDetails)
				|| authenticationService.isUserOwnerOfId(userDetails, employeeId)) {
			imageService.saveImage(employeeId, file);
			return "Image uploaded successfully";
		} else {
			throw new IllegalArgumentException("Employee can update only his/her data");
		}
	}

}
