package com.em.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.em.presentation.R
import com.em.presentation.adapters.CourseAdapter
import com.em.presentation.databinding.FragmentFavBinding
import com.em.presentation.vm.FavViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class FavFragment : Fragment(R.layout.fragment_fav) {
	private lateinit var binding: FragmentFavBinding
	private val vm: FavViewModel by viewModel()
	private lateinit var adapter: CourseAdapter
	
	fun openCourse(id: Long) {
		parentFragmentManager.beginTransaction()
			.replace(
				R.id.container,
				CourseFragment().apply { arguments = Bundle().apply { putLong("id", id) } }
			)
			.addToBackStack(null)
			.commit()
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		binding = FragmentFavBinding.bind(view)
		adapter = CourseAdapter(vm::toggleLike, ::openCourse)
		
		binding.courses.layoutManager = LinearLayoutManager(requireContext())
		binding.courses.adapter = adapter
		
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
		return inflater.inflate(R.layout.fragment_fav, container, false)
	}
}