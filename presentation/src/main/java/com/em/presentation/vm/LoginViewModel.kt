package com.em.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class LoginViewModel : ViewModel() {
	private val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
	
	private val _email = MutableStateFlow("")
	private val _password = MutableStateFlow("")
	
	val canLogin: StateFlow<Boolean> = combine(_email, _password) { email, pwd ->
		email.isNotBlank() && emailRegex.matches(email) && pwd.isNotBlank()
	}.stateIn(viewModelScope, SharingStarted.Lazily, false)
	
	fun onEmailChange(value: String) {
		_email.value = value
	}
	
	fun onPasswordChange(value: String) {
		_password.value = value
	}
}