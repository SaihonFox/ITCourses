package com.em.domain.usecase

import com.em.domain.repository.CourseRepository

class GetCourseUseCase(private val repository: CourseRepository) {
	operator fun invoke() = repository.getCourses()
}