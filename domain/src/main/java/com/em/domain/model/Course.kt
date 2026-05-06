package com.em.domain.model

import java.time.LocalDate

data class Course(
	val id: Long,
	val title: String,
	val text: String,
	val price: String,
	val rate: String,
	val startDate: LocalDate,
	var hasLike: Boolean,
	val publishDate: LocalDate
)