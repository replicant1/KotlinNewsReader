package com.bailey.rod.kotlinnewsreader.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.bailey.rod.kotlinnewsreader.BuildConfig
import com.bailey.rod.kotlinnewsreader.R
import com.bailey.rod.kotlinnewsreader.app.command.CommandEngine
import com.bailey.rod.kotlinnewsreader.app.command.DefaultErrorHandler
import com.bailey.rod.kotlinnewsreader.app.command.DefaultProgressMonitor
import com.bailey.rod.kotlinnewsreader.app.command.ICommandSuccessHandler
import com.bailey.rod.kotlinnewsreader.data.NewsAssetDAO
import com.bailey.rod.kotlinnewsreader.data.NewsAssetListDAO
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import io.reactivex.disposables.Disposable
import org.androidannotations.annotations.*
import timber.log.Timber

/**
 *  Intent that launches this activity should either point to the news JSON or literally contain it. This activity
 *  looks for the JSON by following this search path in order:
 * (1) Literal JSON data supplied in [JSON_STRING_EXTRA] of the launch intent
 * (2) URI to JSON data supplied in the launch intent
 * (3) The default JSON URL as specified in the BuildConfig parameter DEFAULT_NEWS_ASSETS_JSON_URL
 */
@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.menu_main_activity_options)
open class MainActivity : AppCompatActivity() {

	@ViewById(R.id.rv_news_asset_list)
	lateinit var recyclerView: RecyclerView

	@ViewById(R.id.srl_news_asset_list_swipe_refresh_layout)
	lateinit var swipeRefreshLayout: SwipeRefreshLayout

	lateinit var adapter: NewsAssetListAdapter

	lateinit var layoutManager: RecyclerView.LayoutManager

	val newsAssetClickListener: INewsAssetClickListener = NewsAssetClickListener()

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

		val dividerDrawable: Drawable? = ContextCompat.getDrawable(this, R.drawable.divider_news_asset_list)
		if (dividerDrawable != null) {
			recyclerView.addItemDecoration(DividerItemDecoration(dividerDrawable))
		}

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
		} else {
			val urlToLoad: String = intent.dataString ?: BuildConfig.DEFAULT_NEWS_ASSETS_JSON_URL
			Timber.i("Loading feed at URL ${intent.dataString}")

			val disposable: Disposable? = CommandEngine.execute(
					LoadNewsAssetsCommand(urlToLoad),
					DefaultProgressMonitor(this, getString(R.string.news_assets_list_load_progress_msg)),
					LoadNewsAssetsSuccessHandler(),
					DefaultErrorHandler(this, getString(R.string.news_assets_list_load_progress_msg)))
		}
	}

	fun maybeApplyNewsAssetsToList(assets: NewsAssetListDAO?) {
		if ((assets != null) && (assets.assets != null)) {
			adapter = NewsAssetListAdapter(assets.assets, newsAssetClickListener)
			recyclerView.adapter = adapter
		}
		swipeRefreshLayout.isRefreshing = false
	}

	inner class LoadNewsAssetsSuccessHandler : ICommandSuccessHandler {
		override fun onSuccess(result: Any?) {
			val loadedAssets: NewsAssetListDAO? = result as NewsAssetListDAO?
			maybeApplyNewsAssetsToList(loadedAssets)
		}
	}

	inner class NewsAssetClickListener() : INewsAssetClickListener {
		override fun onNewsAssetClick(clickedOn: NewsAssetDAO) {
			Timber.i("News asset with headline ${clickedOn.headline} was clicked")
			NewsAssetActivity_.intent(this@MainActivity).start()
		}
	}

	companion object {
		// Name of the Intent Extra that can be optionally passed into the launch Intent for this
		// activity. It's value should be a string of valid JSON in the Fairfax news asset format.
		val JSON_STRING_EXTRA = "JSON"
	}
}
