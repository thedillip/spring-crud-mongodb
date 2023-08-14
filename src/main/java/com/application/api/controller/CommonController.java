package com.application.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.api.response.ApiEntity;
import com.application.api.response.ApiResponseObject;
import com.application.api.service.CommonService;
import com.application.api.util.ApplicationConstant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommonController {
	private final CommonService commonService;

	@GetMapping(value = "/")
	public ResponseEntity<ApiResponseObject> startBaseApi() {
		log.info("Hitting base API / GET in Controller Layer CommonController::startBaseApi()");
		String message = ApplicationConstant.SUCCESS;
		String response = commonService.startBaseApi();
		return ResponseEntity.ok(new ApiEntity<>(message, response));
	}
}
