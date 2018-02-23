package com.bailey.rod.kotlinnewsreader

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.androidannotations.annotations.EActivity
import timber.log.Timber

@EActivity(R.layout.activity_main)
open class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		// This logging facility needs initialization. Log messages
		// only appear in DEBUG builds, not PRODUCTION builds.
		if (BuildConfig.DEBUG) {
			Timber.plant(Timber.DebugTree())
			Timber.i("KotlinNewsReader started")
		}
	}
}
