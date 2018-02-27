package com.bailey.rod.kotlinnewsreader.common

import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.bailey.rod.kotlinnewsreader.app.command.DefaultProgressMonitor
import com.bailey.rod.kotlinnewsreader.app.mvp.IView

/**
 * Base class for all Activities in this app. Allows an Android Activity to
 * serve as an IView, which includes the capability to show a modal indefinite progress
 * indicator and display an error message in a Toast.
 */
open class BaseViewActivity : AppCompatActivity(), IView {

	private var progressMonitor: DefaultProgressMonitor? = null

	override fun showProgress(progressMessageId: Int) {
		progressMonitor = DefaultProgressMonitor(this, getString(progressMessageId))
		progressMonitor?.startProgress()
	}

	override fun showError(errorMessageId: Int) {
		Toast.makeText(this, getString(errorMessageId), Toast.LENGTH_LONG).show()
	}

	override fun hideProgress() {
		progressMonitor?.stopProgress()
	}
}