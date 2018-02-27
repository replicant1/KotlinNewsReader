package com.bailey.rod.kotlinnewsreader.app.mvp

import android.support.annotation.StringRes

/**
 * Created by rodbailey on 27/2/18.
 */
interface IProgressView {

	fun showProgress(@StringRes progressMessageId: Int)

	fun hideProgress()
}