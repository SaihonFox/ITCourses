package com.em.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.em.domain.model.Course
import com.em.domain.repository.CourseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CourseViewModel(private val repo: CourseRepository) : ViewModel() {
	private val _course = MutableStateFlow<Course?>(null)
	val course = _course.asStateFlow()
	
	fun load(id: Long) = viewModelScope.launch { _course.value = repo.getCourseById(id) }
}