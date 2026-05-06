package com.em.myapplication.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.em.myapplication.R
import com.em.myapplication.databinding.ActivityLoginBinding
import com.em.presentation.nav.LoginNav
import com.em.presentation.ui.LoginFragment

class LoginActivity : AppCompatActivity(R.layout.activity_login), LoginNav {
	private lateinit var binding: ActivityLoginBinding
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		binding = ActivityLoginBinding.inflate(layoutInflater)
		
		setContentView(binding.container)
		
		if (savedInstanceState == null) {
			supportFragmentManager.beginTransaction()
				.replace(binding.container.id, LoginFragment())
				.commit()
		}
	}
	
	override fun onLogin() {
		startActivity(Intent(this, MainActivity::class.java))
		finish()
	}
}