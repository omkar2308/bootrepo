package com.api.book;

import javax.servlet.Servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.book.helper.FileUploadHelper;

@RestController
public class FileUploadController { 
	
	@Autowired
	private FileUploadHelper fileUploadHealper;
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("photo") MultipartFile  file) {
		
//		System.out.println(file.getOriginalFilename()); 
//		System.out.println(file.getSize()); 
//		System.out.println(file.getContentType());
		
		try {
		
		//validations 
		if(file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Select file to upload");
		}
		if (!file.getContentType().equals("image/jpeg")) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("only jpeg file allowed");			
		}
		
		//file upload code 
		
		boolean uploadFile = fileUploadHealper.uploadFile(file);
		if(uploadFile) 
		{
			//return ResponseEntity.ok("file successfully uploaded"); 
			return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
		}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("somthing went wrong");  
	}

}
