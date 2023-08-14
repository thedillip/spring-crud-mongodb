package com.application.api.service;

import java.util.List;

import com.application.api.request.CourseRequest;
import com.application.api.response.CourseResponse;

public interface CourseService {
	String saveCourse(CourseRequest courseRequest);

	List<CourseResponse> findAllCourse();

	CourseResponse findCourseById(String courseId);

	String updateCourseById(String courseId, CourseRequest courseRequest);

	String deleteAllCourse();

	String deleteCourseById(String courseId);
}
