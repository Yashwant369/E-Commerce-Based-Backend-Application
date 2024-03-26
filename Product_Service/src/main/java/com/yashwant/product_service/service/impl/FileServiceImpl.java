package com.yashwant.product_service.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yashwant.product_service.exception.BadRequestException;
import com.yashwant.product_service.service.FileService;
import com.yashwant.product_service.util.FileResponse;

@Service
public class FileServiceImpl implements FileService{

	@Override
	public FileResponse uploadFile(MultipartFile file, String path) {
		// TODO Auto-generated method stub
		String originalName = file.getOriginalFilename();
		String extension = originalName.substring(originalName.lastIndexOf("."));
		String name = UUID.randomUUID().toString();
		String fileName = name + extension;
		String fullPath = path  + fileName;
		if(extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") ||
				extension.equalsIgnoreCase(".jpeg"))
		{
			//throw new BadRequestException("File with extension " + extension + " not allowed ");
			File folder = new File(path);
			if(!folder.exists())	
			{
				folder.mkdirs();
			}
			//folder.mkdirs();
			try {
				Files.copy(file.getInputStream(), Paths.get(fullPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FileResponse response = new FileResponse();
			response.setImageName(fileName);
			response.setMessage("File uploaded : " + fileName);
			response.setSuccess(true);
			response.setStatus(HttpStatus.OK);
			
			return response;
		}
		else  
		{
			throw new BadRequestException("File with extension " + extension + " not allowed ");
		}

	
	}

	@Override
	public InputStream getFile(String path, String fileName) {
		// TODO Auto-generated method stub
		String fullPath = path  + fileName;
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(fullPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputStream;
		
	}
	
	

}
