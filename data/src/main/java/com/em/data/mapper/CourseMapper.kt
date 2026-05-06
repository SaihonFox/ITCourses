package com.em.data.mapper

import com.em.data.dto.CourseDto
import com.em.data.localdb.entity.CourseEntity
import com.em.domain.model.Course

fun CourseDto.toEntity() = CourseEntity(id, title, text, price, rate, startDate, hasLike, publishDate)
fun CourseEntity.toDomain() = Course(id, title, text, price, rate, startDate, hasLike, publishDate)