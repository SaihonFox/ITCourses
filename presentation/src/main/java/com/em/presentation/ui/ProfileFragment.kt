package com.em.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import com.em.presentation.R
import com.em.presentation.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
	private lateinit var binding: FragmentProfileBinding
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		binding = FragmentProfileBinding.bind(view)
		
		ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
			val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.updatePadding(top = bars.top)
			WindowInsetsCompat.CONSUMED
		}
	}
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_profile, container, false)
	}
}