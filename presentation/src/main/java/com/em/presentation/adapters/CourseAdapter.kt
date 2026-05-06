package com.em.presentation.adapters

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.em.domain.model.Course
import com.em.presentation.databinding.ItemCourseBinding
import java.time.format.DateTimeFormatter
import java.util.Locale

class CourseAdapter(private val onLike: (Long) -> Unit, private val onClick: (Long) -> Unit) : ListAdapter<Course, CourseAdapter.ViewHolder>(
	CourseDiffCallback()
) {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false))
	}
	
	override fun onBindViewHolder(holder: ViewHolder, index: Int) {
		holder.bind(getItem(index))
	}
	
	inner class ViewHolder(private val binding: ItemCourseBinding) : RecyclerView.ViewHolder(binding.root) {
		init {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
				val blur = RenderEffect.createBlurEffect(16f, 16f, Shader.TileMode.MIRROR)
				binding.rateCardBg.setRenderEffect(blur)
				binding.dateCardBg.setRenderEffect(blur)
			}
		}
		
		fun bind(course: Course) {
			binding.title.text = course.title
			binding.description.text = course.text
			binding.rate.text = course.rate
			binding.date.text = course.publishDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.getDefault()))
			binding.price.text = "${course.price} ₽"
			
			binding.favBtn.isChecked = course.hasLike
			binding.favBtn.setOnClickListener { onLike(course.id) }
			
			binding.root.setOnClickListener { onClick(course.id) }
		}
	}
}

class CourseDiffCallback : DiffUtil.ItemCallback<Course>() {
	override fun areItemsTheSame(old: Course, new: Course) = old.id == new.id
	
	override fun areContentsTheSame(old: Course, new: Course) = old == new
}