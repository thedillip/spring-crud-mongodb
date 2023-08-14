package com.application.api.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.application.api.response.MediaFile;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommonServiceImpl implements CommonService {

	@Override
	public String startBaseApi() {
		return "Server is UP";
	}

	@Override
	public MediaFile viewApplicationLogs() throws IOException {
		File file = new File("/appliction/logs/spring.log");
		byte[] byteData = Files.readAllBytes(file.toPath());

		return MediaFile.builder().fileName("application.log").fileContentType(MediaType.TEXT_PLAIN_VALUE)
				.byteData(byteData).build();
	}

}
