package com.em.presentation.ui

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.em.domain.model.Course
import com.em.presentation.R
import com.em.presentation.adapters.CourseAdapter
import com.em.presentation.databinding.FragmentMainBinding
import com.em.presentation.vm.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {
	private lateinit var binding: FragmentMainBinding
	private val vm: MainViewModel by viewModel()
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
		
		binding = FragmentMainBinding.bind(view)
		adapter = CourseAdapter(vm::toggleLike, ::openCourse)
		
		ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
			val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.updatePadding(top = bars.top)
			binding.linearLayout2.updatePadding(
				top = TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_DIP,
					16f,
					resources.displayMetrics
				).toInt()
			)
			WindowInsetsCompat.CONSUMED
		}
		
		binding.searchBar.doAfterTextChanged { vm.onSearchChanged(it.toString()) }
		
		binding.courses.layoutManager = LinearLayoutManager(requireContext())
		binding.courses.adapter = adapter
		viewLifecycleOwner.lifecycleScope.launch {
			vm.displayedCourses.collectLatest { list ->
				val newList = ArrayList<Course>(list)
				adapter.submitList(newList)
			}
		}
		binding.llSort.isClickable = true
		binding.llSort.setOnClickListener { vm.toggleSort() }
	}
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_main, container, false)
	}
}