package com.application.api.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommonServiceImpl implements CommonService {

	@Override
	public String startBaseApi() {
		return "Server is UP";
	}

}
