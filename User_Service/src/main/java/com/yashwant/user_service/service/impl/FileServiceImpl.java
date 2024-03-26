package com.yashwant.user_service.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.yashwant.user_service.exception.BadRequestException;
import com.yashwant.user_service.exception.ResourceNotFoundException;
import com.yashwant.user_service.service.FileService;
import com.yashwant.user_service.util.FileResponse;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadFile(MultipartFile file, String path) {
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
			
			return fileName;
		}
		else  
		{
			throw new BadRequestException("File with extension " + extension + " not allowed ");
		}
	}

	@Override
	public InputStream getFile(String path, String name) {
		// TODO Auto-generated method stub
		String fullPath = path  + name;
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(fullPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ResourceNotFoundException("File not found for given file name : " + name);
		}
		return inputStream;

	}
	
	

}
