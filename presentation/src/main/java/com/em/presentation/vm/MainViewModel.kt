package com.em.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.em.domain.model.Course
import com.em.domain.repository.CourseRepository
import com.em.domain.usecase.GetCourseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(uc: GetCourseUseCase, private val repo: CourseRepository) : ViewModel() {
	private val _query = MutableStateFlow("")
	private val _asc = MutableStateFlow(false)
	
	fun onSearchChanged(q: String) {
		_query.value = q
	}
	fun toggleSort() { _asc.value = !_asc.value }
	
	init {
		viewModelScope.launch {
			repo.syncWithServer()
		}
	}
	
	val displayedCourses: StateFlow<List<Course>> = combine(uc(), _query, _asc) { courses, q, asc ->
		val filtered =
			if (q.isBlank()) courses
			else
				courses.filter {
					it.title.contains(q, ignoreCase = true) ||
					it.price.contains(q, ignoreCase = true)
				}
		return@combine if (asc) filtered.sortedBy { it.publishDate } else filtered.sortedByDescending { it.publishDate }
	}.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
	
	fun toggleLike(id: Long) = viewModelScope.launch { repo.toggleLike(id) }
}