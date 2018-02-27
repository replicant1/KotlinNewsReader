package com.bailey.rod.kotlinnewsreader.app.adapt

import com.bailey.rod.kotlinnewsreader.app.command.ICommandErrorHandler
import com.bailey.rod.kotlinnewsreader.app.mvp.IErrorView
import android.support.annotation.StringRes

/**
 * Created by rodbailey on 27/2/18.
 */
class CommandErrorHandlerToErrorViewAdapter(val view: IErrorView?,
											@StringRes val errorMessageId: Int): ICommandErrorHandler {
	override fun showError() {
		view?.showError(errorMessageId)
	}
}