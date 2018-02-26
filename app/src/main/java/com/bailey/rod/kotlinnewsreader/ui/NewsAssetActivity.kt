package com.bailey.rod.kotlinnewsreader.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import com.bailey.rod.kotlinnewsreader.R
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.ViewById
import timber.log.Timber

/**
 *
 */
@EActivity(R.layout.activity_news_asset)
open class NewsAssetActivity : AppCompatActivity() {

	@ViewById(R.id.wv_news_asset)
	@JvmField
	var webView: WebView? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		Timber.d("Here we are in NewsAssetActivity.onCreate")
	}

	@AfterViews
	fun afterViews() {
		Timber.d("Here we are in NewsAssetActivity.afterViews")
		if (intent.data != null) {
			Timber.d("Loading into WebView: url=${intent.dataString}")
			Timber.d("webView=${webView}")
			webView?.loadUrl(intent.dataString)
			// @see WebViewClient to attached load start/finish to progress dialog
		}
	}

	companion object {
		fun start(ctx: Context, uri: Uri) {
			NewsAssetActivity_.intent(ctx).data(uri).start()
		}
	}
}