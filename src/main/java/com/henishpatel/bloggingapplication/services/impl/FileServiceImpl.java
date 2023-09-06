package com.henishpatel.bloggingapplication.services.impl;

import com.henishpatel.bloggingapplication.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		// File name
		String fileName = file.getOriginalFilename();
		// abc.png

		// random fileName generate file
		String randomId = UUID.randomUUID().toString();
		String fileNameWithRandomId = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));

		// Full path
		String filePath = path + File.separator + fileNameWithRandomId;

		// create folder if not created
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdir();
		}

		// file copy

		Files.copy(file.getInputStream(), Paths.get(filePath));

		return fileNameWithRandomId;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path + File.separator + fileName;
		InputStream inputStream = new FileInputStream(fullPath);
		// db logic to return inpustream
		return inputStream;
	}
}
