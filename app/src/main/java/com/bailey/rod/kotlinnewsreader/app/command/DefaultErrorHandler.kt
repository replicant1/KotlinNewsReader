package com.bailey.rod.kotlinnewsreader.app.command

import android.content.Context
import android.support.v7.app.AlertDialog
import com.bailey.rod.kotlinnewsreader.R
import timber.log.Timber

/**
 * Handles an error by producing a simple alert dialog containing a standard error message.
 */
class DefaultErrorHandler(val ctx: Context, val message: String) : ICommandErrorHandler {

	override fun onError(ex: Throwable) {
		Timber.w(ex)

		AlertDialog.Builder(ctx)
				.setTitle(R.string.error_dialog_title)
				.setMessage(message)
				.setCancelable(true)
				.setPositiveButton(ctx.getString(R.string.positive_dialog_button_alt), { dialog, _ -> dialog.cancel() })
				.create()
				.show()
	}
}