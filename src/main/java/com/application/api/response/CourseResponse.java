package com.application.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponse {
	private String id;
	private String courseName;
	private String courseCode;
	private int courseDuration;
	private int totalSemester;
}
