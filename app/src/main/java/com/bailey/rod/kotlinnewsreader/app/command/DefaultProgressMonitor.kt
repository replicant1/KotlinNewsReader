package com.bailey.rod.kotlinnewsreader.app.command

import android.content.Context

/**
 * An indeterminate progress monitor
 */
class DefaultProgressMonitor(val ctx: Context, val message: String): ICommandProgressMonitor {

	private var dialog: IndeterminateProgressDialog? = null

	override fun startProgress() {
		dialog = IndeterminateProgressDialog(ctx, message)
		dialog?.show()
	}

	override fun stopProgress() {
		dialog?.dismiss()
	}
}