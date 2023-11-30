package com.example.demo.uplod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Upload_File {

	public Upload_File() throws IOException {

	}

	public boolean uploadFile(MultipartFile file) {
		boolean f = false;

		try {

			Files.copy(file.getInputStream(),
					Paths.get("src/main/resources/static/img/" + File.separator + file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);

			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

}
