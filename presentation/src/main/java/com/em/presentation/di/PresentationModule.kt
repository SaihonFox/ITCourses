package com.em.presentation.di

import com.em.domain.usecase.GetCourseUseCase
import com.em.presentation.vm.CourseViewModel
import com.em.presentation.vm.FavViewModel
import com.em.presentation.vm.LoginViewModel
import com.em.presentation.vm.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
	viewModel { LoginViewModel() }
	viewModel { MainViewModel(get(), get()) }
	viewModel { FavViewModel(get(), get()) }
	viewModel { CourseViewModel(get()) }
	
	factory { GetCourseUseCase(get()) }
}