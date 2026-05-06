package com.em.data.localdb.converters

import androidx.room.TypeConverter
import java.time.LocalDate

class DateConverter {
	@TypeConverter
	fun fromLocalDate(date: LocalDate): String = date.toString()
	
	@TypeConverter
	fun toLocalDate(date: String): LocalDate = LocalDate.parse(date)
}