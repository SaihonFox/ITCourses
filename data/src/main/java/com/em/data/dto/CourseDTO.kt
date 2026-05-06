package com.em.data.dto

import com.em.domain.model.Course
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
data class CourseDto(
	@param: Json(name = "id") val id: Long,
	@param: Json(name = "title") val title: String,
	@param: Json(name = "text") val text: String,
	@param: Json(name = "price") val price: String,
	@param: Json(name = "rate") val rate: String,
	@param: Json(name = "startDate") val startDate: LocalDate,
	@param: Json(name = "hasLike") val hasLike: Boolean,
	@param: Json(name = "publishDate") val publishDate: LocalDate
) {
	fun toCourseDomain() = Course(
		id = id,
		title = title,
		text = text,
		price = price,
		rate = rate,
		startDate = startDate,
		hasLike = hasLike,
		publishDate = publishDate
	)
}