package com.yashwant.user_service.service;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	public String uploadFile(MultipartFile file, String path);
	public InputStream getFile(String path, String name);

}
