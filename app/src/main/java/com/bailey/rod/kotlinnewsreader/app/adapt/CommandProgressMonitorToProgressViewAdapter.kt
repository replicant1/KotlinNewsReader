package com.bailey.rod.kotlinnewsreader.app.adapt

import android.support.annotation.StringRes
import com.bailey.rod.kotlinnewsreader.app.command.ICommandProgressMonitor
import com.bailey.rod.kotlinnewsreader.app.mvp.IProgressView

/**
 * Created by rodbailey on 27/2/18.
 */
class CommandProgressMonitorToProgressViewAdapter(val view: IProgressView?,
												  @StringRes val progressMessageId: Int) : ICommandProgressMonitor {

	override fun startProgress() {
		view?.showProgress(progressMessageId)
	}

	override fun stopProgress() {
		view?.hideProgress()
	}
}