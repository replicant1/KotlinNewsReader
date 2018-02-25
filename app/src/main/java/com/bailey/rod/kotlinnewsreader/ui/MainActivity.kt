package com.bailey.rod.kotlinnewsreader.ui

import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.bailey.rod.kotlinnewsreader.BuildConfig
import com.bailey.rod.kotlinnewsreader.R
import com.bailey.rod.kotlinnewsreader.data.NewsAssetDAO
import com.bailey.rod.kotlinnewsreader.data.NewsAssetListDAO
import com.bailey.rod.kotlinnewsreader.extensions.assetFileAsString
import com.bailey.rod.kotlinnewsreader.extensions.loadFile
import org.androidannotations.annotations.*
import timber.log.Timber
import java.net.URL

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
		}

		println("-----------------------------")
		println("Launch intent.data=${intent.data}, intent.dataString=${intent.dataString}")
		println("Has extra called $JSON_STRING_EXTRA = " + intent.hasExtra(JSON_STRING_EXTRA))
		println("-----------------------------")
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
		if (intent.hasExtra(JSON_STRING_EXTRA)) {
			val jsonString = intent.getStringExtra(JSON_STRING_EXTRA)
			println("Loading literal JSON from extra")
			maybeApplyNewsAssetsToList(NewsAssetListDAO.parseAssetJson(jsonString))
		}
		else if (intent.data != null) {
			// Get default URL from BuildConfig?
			val feedURL: String = intent.dataString ?: "https://bruce-v2-mob.fairfaxmedia.com.au/1/coding_test/13ZZQX/full"
			Timber.i("Loading feed at URL $feedURL")
			SimpleURLFileLoadTask(feedURL).execute()
		}
	}

	fun maybeApplyNewsAssetsToList(assets: NewsAssetListDAO?) {
		if ((assets != null) && (assets.assets != null)) {
			adapter = NewsAssetListAdapter(assets.assets)
			recyclerView.adapter = adapter
		}
		swipeRefreshLayout.isRefreshing = false
	}

	inner class SimpleURLFileLoadTask(val url : String) : AsyncTask<Void, Void, NewsAssetListDAO?>() {

		override fun doInBackground(vararg p0: Void?): NewsAssetListDAO? {
			val jsonStr: String? = URL(url).loadFile()
			if (jsonStr != null) {
				val allAssets = NewsAssetListDAO.parseAssetJson(jsonStr)
				if ((allAssets != null) && (allAssets.assets != null)) {
					return NewsAssetListDAO.parseAssetJson(jsonStr)
				}
			}
			return null
		}

		override fun onPostExecute(result: NewsAssetListDAO?) {
			maybeApplyNewsAssetsToList(result)
		}

	}

	companion object {
		val JSON_STRING_EXTRA = "JSON"
		private val STATIC_ASSETS: List<NewsAssetDAO> = listOf(
				NewsAssetDAO(0, "Headline 0", "Abstract 0", "Byline 0", null, null),
				NewsAssetDAO(1, "Headline 1", "Abstract 1", "Byline 1", null, null),
				NewsAssetDAO(2, "Headline 2", "Abstract 2", "Byline 2", null, null)
		)
	}
}
