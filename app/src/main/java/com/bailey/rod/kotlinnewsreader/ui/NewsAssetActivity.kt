package com.bailey.rod.kotlinnewsreader.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bailey.rod.kotlinnewsreader.R
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import timber.log.Timber

/**
 *
 */
@EActivity(R.layout.activity_news_asset)
open class NewsAssetActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		Timber.d("Here we are in NewsAssetActivity.onCreate")
	}

	@AfterViews
	fun afterViews() {
		Timber.d("Here we are in NewsAssetActivity.afterViews")
	}

	companion object {

	}
}