package com.application.api.controller;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping(value = "/course/{courseId}")
	public ResponseEntity<ApiResponseObject> findCourseById(@PathVariable(name = "courseId") final String courseId) {
		log.info(
				"Hitting /course/{} GET API in Controller Layer -- CourseController::findCourseById() :: Path Variable :: courseId = {}",
				courseId, courseId);
		HttpStatus status = null;
		String message = null;
		CourseResponse response = courseService.findCourseById(courseId);
		if (response != null) {
			status = HttpStatus.OK;
			message = ApplicationConstant.DATA_FOUND;
		}
		log.info("Returned Response from /course/{} GET API :: Response = {}", courseId,
				JavaToJsonConverter.convert(response));
		return new ResponseEntity<>(new ApiEntity<>(message, response), status);
	}

	@PutMapping(value = "/course/{courseId}")
	public ResponseEntity<ApiResponseObject> updateCourseById(@PathVariable(name = "courseId") final String courseId,
			@RequestBody final CourseRequest courseRequest) {
		log.info(
				"Hitting /course/{} PUT API in Controller Layer -- CourseController::updateCourseById() :: Path Variable :: courseId = {} :: Request Body = {}",
				courseId, courseId, JavaToJsonConverter.convert(courseRequest));
		HttpStatus status = null;
		String message = null;
		String response = courseService.updateCourseById(courseId, courseRequest);
		if (response.equals(ApplicationConstant.UPDATED)) {
			status = HttpStatus.OK;
			message = "Course Details has been updated with courseId : " + courseId;
		}
		log.info("Returned Response from /course/{} PUT API = {}", courseId, response);
		return new ResponseEntity<>(new ApiEntity<>(message, response), status);
	}

	@DeleteMapping(value = "/course")
	public ResponseEntity<ApiResponseObject> deleteAllCourse() {
		log.info("Hitting /course DELETE API in Controller Layer -- CourseController::deleteAllCourse()");
		HttpStatus status = null;
		String message = null;
		String response = courseService.deleteAllCourse();
		if (response.equals(ApplicationConstant.DELETED)) {
			status = HttpStatus.OK;
			message = "Courses has been deleted";
		}
		log.info("Returned Response from /course DELETE API = {}", response);
		return new ResponseEntity<>(new ApiEntity<>(message, response), status);
	}

	@DeleteMapping(value = "/course/{courseId}")
	public ResponseEntity<ApiResponseObject> deleteCourseById(@PathVariable(name = "courseId") final String courseId) {
		log.info(
				"Hitting /course/{} DELETE API in Controller Layer -- CourseController::deleteCourseById() :: Path Variable :: courseId = {}",
				courseId);
		HttpStatus status = null;
		String message = null;
		String response = courseService.deleteCourseById(courseId);
		if (response.equals(ApplicationConstant.DELETED)) {
			status = HttpStatus.OK;
			message = "Course has been deleted with courseId : " + courseId;
		}
		log.info("Returned Response from /course/{} DELETE API = {}", courseId, response);
		return new ResponseEntity<>(new ApiEntity<>(message, response), status);
	}
}
