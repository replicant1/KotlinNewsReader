package com.bailey.rod.kotlinnewsreader.app.mvp

import android.support.annotation.StringRes

/**
 * This tag interface is used throughout the app to identify a screen
 * that has a counterpart Presenter behind it.
 *
 * @see IPresenter
 */
interface IView: IProgressView, IErrorView