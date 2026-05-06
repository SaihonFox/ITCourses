package com.em.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.em.domain.repository.CourseRepository
import com.em.domain.usecase.GetCourseUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavViewModel(uc: GetCourseUseCase, private val repo: CourseRepository) : ViewModel() {
	val favCourses = repo.getCourses().map { it.filter { c -> c.hasLike } }.stateIn(viewModelScope,
		SharingStarted.WhileSubscribed(5000), emptyList())
	
	fun toggleLike(id: Long) = viewModelScope.launch { repo.toggleLike(id) }
}