package com.em.myapplication

import android.app.Application
import com.em.data.di.dataModule
import com.em.myapplication.di.appModule
import com.em.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
	override fun onCreate() {
		super.onCreate()
		
		startKoin {
			androidContext(this@App)
			modules(appModule, dataModule, presentationModule)
		}
	}
}