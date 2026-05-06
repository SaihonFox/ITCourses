package com.em.presentation.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.em.presentation.R
import com.em.presentation.databinding.FragmentLoginBinding
import com.em.presentation.vm.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.core.net.toUri
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.em.presentation.nav.LoginNav
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {
	private lateinit var binding: FragmentLoginBinding
	private val vm: LoginViewModel by viewModel()
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		binding = FragmentLoginBinding.bind(view)
		
		viewLifecycleOwner.lifecycleScope.launch { vm.canLogin.collectLatest { binding.enterBtn.isEnabled = it } }
		binding.email.doAfterTextChanged { vm.onEmailChange(it.toString()) }
		binding.password.doAfterTextChanged { vm.onPasswordChange(it.toString()) }
		
		binding.vkBtn.setOnClickListener {
			startActivity(Intent(Intent.ACTION_VIEW, "https://vk.com/".toUri()))
		}
		binding.okBtn.setOnClickListener {
			startActivity(Intent(Intent.ACTION_VIEW, "https://ok.ru/".toUri()))
		}
		binding.enterBtn.setOnClickListener {
			if(vm.canLogin.value)
				(requireActivity() as? LoginNav)?.onLogin()
		}
	}
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_login, container, false)
	}
}