package com.em.presentation.ui

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updateMargins
import androidx.core.view.updatePadding
import androidx.lifecycle.lifecycleScope
import com.em.presentation.R
import com.em.presentation.databinding.FragmentCourseBinding
import com.em.presentation.vm.CourseViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

class CourseFragment : Fragment(R.layout.fragment_course) {
	private lateinit var binding: FragmentCourseBinding
	private val vm: CourseViewModel by viewModel()
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		binding = FragmentCourseBinding.bind(view)
		
		ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, _ ->
			view.updatePadding(top = 0)
			WindowInsetsCompat.CONSUMED
		}
		topInset(binding.backBtn, binding.favBtn)
		
		binding.backBtn.setOnClickListener {
			parentFragmentManager.popBackStack()
		}
		
		val id = arguments?.getLong("id", -1)!!
		if(id == -1L)
			return
		
		vm.load(id).invokeOnCompletion {
			viewLifecycleOwner.lifecycleScope.launch {
				val course = vm.course.value
				if (course == null) {
					Toast.makeText(context, "Курс не найден", Toast.LENGTH_SHORT).show()
					return@launch
				}
				
				binding.title.text = course.title
				binding.rate.text = course.rate
				binding.date.text =
					course.publishDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
			}
		}
	}
	
	private fun topInset(vararg views: View) {
		ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
			val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			Toast.makeText(context, "Bar: ${bars.top.pxToDp(requireContext())}", Toast.LENGTH_SHORT).show()
			for(view in views) {
				val params = view.layoutParams as ViewGroup.MarginLayoutParams
				params.updateMargins(top = bars.top)
				view.layoutParams = params
			}
			
			WindowInsetsCompat.CONSUMED
		}
	}
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_course, container, false)
	}
}

fun Int.dpToPx(context: Context): Int {
	val metrics = context.resources.displayMetrics
	return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), metrics).toInt()
}

fun Int.pxToDp(context: Context): Int {
	val metrics = context.resources.displayMetrics
	return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, this.toFloat(), metrics).toInt()
}