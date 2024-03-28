package com.yashwant.user_service.service;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.yashwant.user_service.util.FileResponse;

public interface FileService {
	
	public FileResponse uploadFile(MultipartFile file, String path);
	public InputStream getFile(String path, String name);

}
