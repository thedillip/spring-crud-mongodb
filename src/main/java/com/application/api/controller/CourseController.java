package com.application.api.controller;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.api.request.CourseRequest;
import com.application.api.response.ApiEntity;
import com.application.api.response.ApiResponseObject;
import com.application.api.response.CourseResponse;
import com.application.api.service.CourseService;
import com.application.api.util.ApplicationConstant;
import com.application.api.util.JavaToJsonConverter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/course-service")
@RequiredArgsConstructor
@Slf4j
public class CourseController {

	private final CourseService courseService;

	@PostMapping(value = "/course")
	public ResponseEntity<ApiResponseObject> saveCourse(@RequestBody final CourseRequest courseRequest) {
		log.info("Hitting /course POST API in Controller Layer -- CourseController::saveCourse() :: Request Body = {}",
				JavaToJsonConverter.convert(courseRequest));
		HttpStatus status = null;
		String message = null;
		String response = courseService.saveCourse(courseRequest);
		if (response.equals(ApplicationConstant.CREATED)) {
			status = HttpStatus.CREATED;
			message = "Course Added Successfully";
		}
		log.info("Returned Response from /course POST API = {}", response);
		return new ResponseEntity<>(new ApiEntity<>(message, response), status);
	}

	@GetMapping(value = "/course")
	public ResponseEntity<ApiResponseObject> findAllCourse() {
		log.info("Hitting /course GET API in Controller Layer -- CourseController::findAllCourse()");
		HttpStatus status = null;
		String message = null;
		List<CourseResponse> response = courseService.findAllCourse();
		if (CollectionUtils.isNotEmpty(response)) {
			status = HttpStatus.OK;
			message = ApplicationConstant.DATA_FOUND;
		} else if (CollectionUtils.isEmpty(response)) {
			status = HttpStatus.NOT_FOUND;
			message = ApplicationConstant.DATA_NOT_FOUND;
		}
		log.info("Returned Response from /course GET API :: Response List Size = {}", response.size());
		return new ResponseEntity<>(new ApiEntity<>(message, response), status);
	}
}
