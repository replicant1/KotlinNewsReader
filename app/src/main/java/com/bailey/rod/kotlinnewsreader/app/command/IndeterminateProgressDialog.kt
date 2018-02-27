package com.bailey.rod.kotlinnewsreader.app.command

import android.app.ProgressDialog
import android.content.Context

/**
 * Convenience subclass of ProgressDialog
 */
class IndeterminateProgressDialog(ctx: Context, msg: String) : ProgressDialog(ctx) {
	init {
		setMessage(msg)
		setCancelable(false)
		isIndeterminate = true
	}
}