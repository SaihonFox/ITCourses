package com.em.data.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateJsonAdapter {
	val FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE
	
	@ToJson
	fun toJson(date: LocalDate): String = date.format(FORMATTER)
	
	@FromJson
	fun fromJson(date: String): LocalDate = LocalDate.parse(date, FORMATTER)
}