package com.bailey.rod.kotlinnewsreader.newsassetlist.view

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.bailey.rod.kotlinnewsreader.BuildConfig
import com.bailey.rod.kotlinnewsreader.R
import com.bailey.rod.kotlinnewsreader.common.BaseViewActivity
import com.bailey.rod.kotlinnewsreader.data.dao.NewsAssetDAO
import com.bailey.rod.kotlinnewsreader.data.dao.NewsAssetListDAO
import com.bailey.rod.kotlinnewsreader.newsasset.NewsAssetActivity
import com.bailey.rod.kotlinnewsreader.newsassetlist.pres.INewsAssetListPresenter
import com.bailey.rod.kotlinnewsreader.newsassetlist.pres.NewsAssetListPresenter
import com.bailey.rod.kotlinnewsreader.newsassetlist.view.NewsAssetListActivity.Companion.JSON_STRING_INTENT_EXTRA
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import org.androidannotations.annotations.*
import timber.log.Timber
import java.net.URISyntaxException
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent



/**
 *  Intent that launches this activity should either point to the news JSON or literally contain it. This activity
 *  looks for the JSON by following this search path in order:
 *
 * (1) Literal JSON data supplied in [JSON_STRING_INTENT_EXTRA] of the launch intent
 * (2) URI to JSON data supplied in the launch intent
 * (3) The default JSON URL as specified in [BuildConfig.DEFAULT_NEWS_ASSETS_JSON_URL]
 */
@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.menu_main_activity_options)
open class NewsAssetListActivity : BaseViewActivity(), INewsAssetListView {

	private val presenter: INewsAssetListPresenter = NewsAssetListPresenter()

	@ViewById(R.id.rv_news_asset_list)
	lateinit var recyclerView: RecyclerView

	@ViewById(R.id.srl_news_asset_list_swipe_refresh_layout)
	lateinit var swipeRefreshLayout: SwipeRefreshLayout

	private var adapter: NewsAssetListAdapter? = null

	private val newsAssetClickListener: INewsAssetListItemClickListener = NewsAssetClickListener()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		// Timber logging facility is initialized here. Log messages
		// only appear in DEBUG builds, not PRODUCTION builds.
		if (BuildConfig.DEBUG) {
			Timber.plant(Timber.DebugTree())
			Timber.i("KotlinNewsReader started")
		}
	}

	@AfterViews
	@Trace
	fun afterViews() {
		Timber.d("Into afterViews() with presenter=$presenter")
		recyclerView.layoutManager = LinearLayoutManager(this)

		val dividerDrawable: Drawable? = ContextCompat.getDrawable(this, R.drawable.divider_news_asset_list)
		if (dividerDrawable != null) {
			recyclerView.addItemDecoration(DividerItemDecoration(dividerDrawable))
		}

		swipeRefreshLayout.setOnRefreshListener({
			Timber.d("Swipe refresh triggered - reload news asset list data")
			loadNewsAssetList()
		})
	}

	override fun onPause() {
		Timber.d("Into onPause() with presenter = $presenter")
		super.onPause()
		presenter.onDetachView()
	}

	override fun onResume() {
		Timber.d("Into onResume() with presenter=$presenter")
		super.onResume()
		presenter.onAttachView(this, null)

		// The very first time this activity appears, automatically kick off a load of the news data
		if (adapter == null) {
			loadNewsAssetList()
		}
	}

	@OptionsItem(R.id.menu_item_refresh_news_assets_list)
	fun loadNewsAssetList() {
		presenter.loadNewsAssetList(intent)
	}

	override fun refresh(listData: NewsAssetListDAO?) {
		if ((listData != null) && (listData.assets != null)) {
			adapter = NewsAssetListAdapter(listData.assets, newsAssetClickListener)
			recyclerView.adapter = adapter
		}
		swipeRefreshLayout.isRefreshing = false
	}

	inner class NewsAssetClickListener() : INewsAssetListItemClickListener {
		override fun onNewsAssetClick(clickedOn: NewsAssetDAO) {
			Timber.i("News asset with headline \"${clickedOn.headline}\" was clicked")
			try {
				val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(clickedOn.url))
				startActivity(browserIntent)
			} catch (px: URISyntaxException) {
				Timber.w("User clicked on news asset with un-parsable URI for news asset", px)
				showError(R.string.news_assets_list_uri_parse_error)
			}
		}
	}

	companion object {
		// Name of the Intent Extra that can be optionally passed into the launch Intent for this
		// activity. It's value should be a string of valid JSON in the Fairfax news asset format.
		val JSON_STRING_INTENT_EXTRA = "com.bailey.rod.kotlinnewsreader.intent.extra.JSON"
	}
}
