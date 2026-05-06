package com.em.data.repository

import com.em.data.api.CourseAPI
import com.em.data.localdb.dao.CourseDao
import com.em.data.mapper.toDomain
import com.em.data.mapper.toEntity
import com.em.domain.model.Course
import com.em.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CourseRepositoryImpl(private val api: CourseAPI, private val dao: CourseDao) : CourseRepository {
	override fun getCourses(): Flow<List<Course>> {
		return dao.getCourses().map { list -> list.map { it.toDomain() } }
	}
	
	override suspend fun syncWithServer() {
		try {
			val response = api.getCourses()
			if (!response.isSuccessful)
				return
			
			val body = response.body()
			dao.insertRange(body?.map { it.toEntity() }.orEmpty())
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}
	
	override suspend fun toggleLike(id: Long) = dao.toggleLike(id)
	
	override suspend fun getCourseById(id: Long): Course? = dao.getById(id)?.toDomain()
}