package com.em.data.localdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "courses")
data class CourseEntity(
	@PrimaryKey val id: Long,
	val title: String,
	val text: String,
	val price: String,
	val rate: String,
	val startDate: LocalDate,
	val hasLike: Boolean,
	val publishDate: LocalDate
)
