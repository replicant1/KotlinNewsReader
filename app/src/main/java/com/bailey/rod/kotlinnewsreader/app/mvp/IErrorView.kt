package com.bailey.rod.kotlinnewsreader.app.mvp

import android.support.annotation.IdRes
import android.support.annotation.StringRes

/**
 * Created by rodbailey on 27/2/18.
 */
interface IErrorView {

	fun showError(@StringRes errorMessageId: Int)
}