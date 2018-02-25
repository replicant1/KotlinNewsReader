package com.bailey.rod.kotlinnewsreader.ui

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.bailey.rod.kotlinnewsreader.BuildConfig
import com.bailey.rod.kotlinnewsreader.R
import com.bailey.rod.kotlinnewsreader.data.NewsAssetDAO
import org.androidannotations.annotations.*
import timber.log.Timber

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.menu_main_activity_options)
open class MainActivity : AppCompatActivity() {

	@ViewById(R.id.rv_news_asset_list)
	lateinit var recyclerView: RecyclerView

	@ViewById(R.id.srl_news_asset_list_swipe_refresh_layout)
	lateinit var swipeRefreshLayout: SwipeRefreshLayout

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

		loadNewsAssetsAsync()

		swipeRefreshLayout.setOnRefreshListener(
				SwipeRefreshLayout.OnRefreshListener {
					println("Refresh - trigger asynch reload of data and show progress monitor")
					loadNewsAssetsAsync()
		})
	}


	@OptionsItem(R.id.menu_item_refresh_news_assets_list)
	fun loadNewsAssetsAsync() {
		adapter = NewsAssetListAdapter(STATIC_ASSETS)
		recyclerView.adapter = adapter
		swipeRefreshLayout.isRefreshing = false
	}

	companion object {
		private val STATIC_ASSETS: List<NewsAssetDAO> = listOf(
				NewsAssetDAO(0, "Headline 0", "Abstract 0", "Byline 0", null, null),
				NewsAssetDAO(1, "Headline 1", "Abstract 1", "Byline 1", null, null),
				NewsAssetDAO(2, "Headline 2", "Abstract 2", "Byline 2", null, null)
		)
	}
}
