package com.em.data.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.em.data.localdb.converters.DateConverter
import com.em.data.localdb.dao.CourseDao
import com.em.data.localdb.entity.CourseEntity

@Database(entities = [CourseEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class CoursesDB : RoomDatabase() {
	abstract fun courseDao(): CourseDao
}