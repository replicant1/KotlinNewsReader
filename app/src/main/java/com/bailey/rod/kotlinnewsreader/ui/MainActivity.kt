package com.bailey.rod.kotlinnewsreader.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.bailey.rod.kotlinnewsreader.BuildConfig
import com.bailey.rod.kotlinnewsreader.R
import com.bailey.rod.kotlinnewsreader.data.NewsAssetDAO
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.ViewById
import timber.log.Timber

@EActivity(R.layout.activity_main)
open class MainActivity : AppCompatActivity() {

	@ViewById(R.id.rv_news_asset_list)
	lateinit var recyclerView: RecyclerView

	lateinit var adapter: NewsAssetListAdapter

	lateinit var layoutManager: RecyclerView.LayoutManager

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		// This logging facility needs initialization. Log messages
		// only appear in DEBUG builds, not PRODUCTION builds.
		if (BuildConfig.DEBUG) {
			Timber.plant(Timber.DebugTree())
			Timber.i("KotlinNewsReader started")
			Timber.i("NEWS_DATA_URL = ${BuildConfig.NEWS_DATA_URL}")
		}
	}

	@AfterViews
	fun afterViews() {
		layoutManager = LinearLayoutManager(this)
		recyclerView.layoutManager = layoutManager

		adapter = NewsAssetListAdapter(STATIC_ASSETS)
		recyclerView.adapter = adapter
	}

	companion object {
		private val STATIC_ASSETS: List<NewsAssetDAO> = listOf(
				NewsAssetDAO(0, "Headline 0", "Abstract 0", "Byline 0", null, null),
				NewsAssetDAO(1, "Headline 1", "Abstract 1", "Byline 1", null, null),
				NewsAssetDAO(2, "Headline 2", "Abstract 2", "Byline 2", null, null)
		)
	}
}
