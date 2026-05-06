package com.em.myapplication.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.em.myapplication.R
import com.em.myapplication.databinding.ActivityMainBinding
import com.em.presentation.ui.FavFragment
import com.em.presentation.ui.MainFragment
import com.em.presentation.ui.ProfileFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
	private lateinit var binding: ActivityMainBinding
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.main)
		
		binding.bottomNav.setOnItemSelectedListener { item ->
			loadFragment(item.itemId)
			true
		}
		if(savedInstanceState == null)
			loadFragment(com.em.presentation.R.id.main_menu)
	}
	
	private fun loadFragment(itemId: Int) {
		val fragment = when (itemId) {
			com.em.presentation.R.id.main_menu -> MainFragment()
			com.em.presentation.R.id.fav_menu -> FavFragment()
			com.em.presentation.R.id.profile_menu -> ProfileFragment()
			else -> MainFragment()
		}
		supportFragmentManager.beginTransaction()
			.replace(binding.container.id, fragment)
			.commit()
	}
}