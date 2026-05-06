package com.em.data.localdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.em.data.localdb.entity.CourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
	@Query("select * from courses")
	fun getCourses(): Flow<List<CourseEntity>>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertRange(courses: List<CourseEntity>)
	
	@Query("update courses set hasLike = not hasLike where id = :id")
	suspend fun toggleLike(id: Long)
	
	@Query("select * from courses where id = :id")
	suspend fun getById(id: Long): CourseEntity?
}