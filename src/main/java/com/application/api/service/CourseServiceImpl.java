package com.application.api.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.application.api.document.CourseDocument;
import com.application.api.exception.ResourceNotFound;
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
	public String saveCourse(final CourseRequest courseRequest) {
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
	public CourseResponse findCourseById(final String courseId) {
		log.info("Inside Service Layer :: CourseServiceImpl::findCourseById({})", courseId);
		CourseDocument courseDocument = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFound("Course not Found with courseId : " + courseId));
		return modelMapper.map(courseDocument, CourseResponse.class);
	}

	@Override
	public String updateCourseById(final String courseId, final CourseRequest courseRequest) {
		log.info("Inside Service Layer :: CourseServiceImpl::updateCourseById({}, {})", courseId, courseRequest);
		String message = ApplicationConstant.ERROR;
		CourseDocument courseDocument = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFound("Course not Found with courseId : " + courseId));
		courseDocument.setCourseName(courseRequest.getCourseName());
		courseDocument.setCourseCode(courseRequest.getCourseCode());
		courseDocument.setCourseDuration(courseRequest.getCourseDuration());
		courseDocument.setTotalSemester(courseRequest.getTotalSemester());
		courseRepository.save(courseDocument);
		message = ApplicationConstant.UPDATED;
		return message;
	}

	@Override
	public String deleteAllCourse() {
		log.info("Inside Service Layer :: CourseServiceImpl::deleteAllCourse()");
		if (courseRepository.count() == 0L)
			throw new ResourceNotFound("Courses not available for deletion");
		String message = ApplicationConstant.ERROR;
		courseRepository.deleteAll();
		message = ApplicationConstant.DELETED;
		return message;
	}

	@Override
	public String deleteCourseById(final String courseId) {
		log.info("Inside Service Layer :: CourseServiceImpl::deleteCourseById({})", courseId);
		CourseDocument courseDocument = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFound("Course not Found with courseId : " + courseId));
		String message = ApplicationConstant.ERROR;
		courseRepository.deleteById(courseDocument.getId());
		message = ApplicationConstant.DELETED;
		return message;
	}

}
