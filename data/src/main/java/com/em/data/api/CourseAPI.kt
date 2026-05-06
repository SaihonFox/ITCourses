package com.em.data.api

import com.em.data.dto.CourseDto
import retrofit2.Response
import retrofit2.http.GET

interface CourseAPI {
	@GET("api/courses")
	suspend fun getCourses(): Response<List<CourseDto>>
}