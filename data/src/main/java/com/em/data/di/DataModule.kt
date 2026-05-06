package com.em.data.di

import androidx.room.Room
import com.em.data.adapters.LocalDateJsonAdapter
import com.em.data.api.CourseAPI
import com.em.data.localdb.CoursesDB
import com.em.data.repository.CourseRepositoryImpl
import com.em.domain.repository.CourseRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {
	single { Room.databaseBuilder(get(), CoursesDB::class.java, "courses.db").build() }
	single { get<CoursesDB>().courseDao() }
	
	single { OkHttpClient.Builder()
		.readTimeout(30, TimeUnit.SECONDS)
		.callTimeout(30, TimeUnit.SECONDS)
		.connectTimeout(30, TimeUnit.SECONDS)
		.build() }
	
	single {
		val moshi = Moshi.Builder().add(LocalDateJsonAdapter()).addLast(KotlinJsonAdapterFactory()).build()
		
		Retrofit.Builder()
			.baseUrl("http://10.0.2.2:5272/")
			.addConverterFactory(MoshiConverterFactory.create(moshi))
			.build()
	}
	
	single { get<Retrofit>().create(CourseAPI::class.java) }
	
	single<CourseRepository> { CourseRepositoryImpl(get(), get()) }
}