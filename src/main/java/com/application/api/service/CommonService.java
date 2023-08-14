package com.application.api.service;

import java.io.IOException;

import com.application.api.response.MediaFile;

public interface CommonService {
	String startBaseApi();

	MediaFile viewApplicationLogs() throws IOException;
}
