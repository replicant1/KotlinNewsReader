package com.bailey.rod.kotlinnewsreader.app

import android.app.Application
import com.squareup.otto.Bus
import com.squareup.otto.ThreadEnforcer

/**
 * Created by rodbailey on 27/2/18.
 */
class KotlinNewsReaderApplication: Application() {

	companion object {
		// Global event bus
		val bus: Bus = Bus(ThreadEnforcer.MAIN)
	}
}