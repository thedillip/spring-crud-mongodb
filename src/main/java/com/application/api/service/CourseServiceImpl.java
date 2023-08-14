package com.application.api.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.application.api.document.CourseDocument;
import com.application.api.repository.CourseRepository;
import com.application.api.request.CourseRequest;
import com.application.api.response.CourseResponse;
import com.application.api.util.ApplicationConstant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

	private final ModelMapper modelMapper;
	private final CourseRepository courseRepository;

	@Override
	public String saveCourse(CourseRequest courseRequest) {
		log.info("Inside Service Layer :: CourseServiceImpl::saveCourse()");
		String message = ApplicationConstant.ERROR;
		CourseDocument courseDocument = modelMapper.map(courseRequest, CourseDocument.class);
		courseRepository.save(courseDocument);
		message = ApplicationConstant.CREATED;
		return message;
	}

	@Override
	public List<CourseResponse> findAllCourse() {
		log.info("Inside Service Layer :: CourseServiceImpl::findAllCourse()");
		List<CourseDocument> courseDocumentList = courseRepository.findAll();
		return courseDocumentList.stream().map(courseDocument -> modelMapper.map(courseDocument, CourseResponse.class))
				.toList();
	}

	@Override
	public CourseResponse findCourseById(String courseId) {
		return null;
	}

	@Override
	public String updateCourseById(String courseId, CourseRequest courseRequest) {
		return null;
	}

	@Override
	public String deleteAllCourse() {
		return null;
	}

	@Override
	public String deleteCourseById(String courseId) {
		return null;
	}

}
