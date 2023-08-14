package com.application.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.application.api.document.CourseDocument;

public interface CourseRepository extends MongoRepository<CourseDocument, String> {

}
