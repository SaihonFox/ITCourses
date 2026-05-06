package com.em.domain.repository

import com.em.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
	fun getCourses(): Flow<List<Course>>
	
	suspend fun syncWithServer()
	
	suspend fun toggleLike(id: Long)
	
	suspend fun getCourseById(id: Long): Course?
}