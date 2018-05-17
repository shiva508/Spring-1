package com.spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	@GetMapping("/")
	public String fileUploadForm(Model model)
	{
		return "fileUploadForm";
	}
	
	@PostMapping("/fileUpload")
	public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file) throws IOException
	{
		if(!file.getOriginalFilename().isEmpty())
		{
			 BufferedOutputStream outputStream = new BufferedOutputStream(
		               new FileOutputStream(
		                     new File("D:/Shiva/gate", file.getOriginalFilename())));
			outputStream.write(file.getBytes());
			outputStream.flush();
			outputStream.close();
		}
		else
		{
			return new ResponseEntity<>("Invalid file.",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("File Uploaded Successfully.",HttpStatus.OK);
	}
}
