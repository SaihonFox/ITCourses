package com.em.presentation.ui

import android.content.Context
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
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
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
			val blur = RenderEffect.createBlurEffect(16f, 16f, Shader.TileMode.MIRROR)
			binding.rateCardBg.setRenderEffect(blur)
			binding.dateCardBg.setRenderEffect(blur)
		}
		
		ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
			val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.updatePadding(top = 0)
			
			for(view in listOf(binding.backBtn, binding.favBtn)) {
				val params = view.layoutParams as ViewGroup.MarginLayoutParams
				params.updateMargins(top = bars.top)
				view.layoutParams = params
			}
			
			WindowInsetsCompat.CONSUMED
		}
		
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
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_course, container, false)
	}
}