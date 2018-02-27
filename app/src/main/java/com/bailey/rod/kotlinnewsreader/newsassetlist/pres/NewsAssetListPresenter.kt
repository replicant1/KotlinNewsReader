package com.bailey.rod.kotlinnewsreader.newsassetlist.pres

import android.content.Intent
import com.bailey.rod.kotlinnewsreader.BuildConfig
import com.bailey.rod.kotlinnewsreader.R
import com.bailey.rod.kotlinnewsreader.app.CommandErrorHandlerToErrorViewAdapter
import com.bailey.rod.kotlinnewsreader.app.CommandProgressMonitorToProgressViewAdapter
import com.bailey.rod.kotlinnewsreader.app.command.CommandEngine
import com.bailey.rod.kotlinnewsreader.app.command.ICommandSuccessHandler
import com.bailey.rod.kotlinnewsreader.data.dao.NewsAssetListDAO
import com.bailey.rod.kotlinnewsreader.newsassetlist.view.INewsAssetListView
import com.bailey.rod.kotlinnewsreader.newsassetlist.view.NewsAssetListActivity
import com.google.gson.JsonParseException
import io.reactivex.disposables.Disposable
import timber.log.Timber

/**
 * Presenter for the list of news article summaries provided in onAttachView()
 */
class NewsAssetListPresenter : INewsAssetListPresenter {

	private var view: INewsAssetListView? = null

	private var disposable: Disposable? = null

	override fun loadNewsAssetList(intent: Intent) {
		Timber.d("Into loadNewsAssetList with view=${view}")

		if (intent.hasExtra(NewsAssetListActivity.JSON_STRING_INTENT_EXTRA)) {
			Timber.i("Loading literal JSON from extra")
			val jsonString = intent.getStringExtra(NewsAssetListActivity.JSON_STRING_INTENT_EXTRA)
			try {
				view?.refresh(NewsAssetListDAO.parseAssetJson(jsonString))
			} catch (ex: JsonParseException) {
				Timber.w("Activity intent extra contained un-parsable JSON")
				view?.showError(R.string.news_assets_list_load_error_msg)
			}
		} else {
			val urlToLoad: String

			if (intent.dataString == null) {
				Timber.i("Loading JSON file from default URI in BuildConfig: ${BuildConfig
						.DEFAULT_NEWS_ASSETS_JSON_URL}")
				urlToLoad = BuildConfig.DEFAULT_NEWS_ASSETS_JSON_URL
			} else {
				Timber.i("Loading JSON file from URI in Intent: ${intent.dataString}")
				urlToLoad = intent.dataString
			}

			if (view == null) {
				Timber.i("Not loading assets from URI because view == null");
			} else {
				Timber.i("Begin loading assets from URI into view $view")
				disposable = CommandEngine.execute(
						LoadNewsAssetListCommand(urlToLoad),
						CommandProgressMonitorToProgressViewAdapter(view, R.string.news_assets_list_load_progress_msg),
						LoadNewsAssetsSuccessHandler(),
						CommandErrorHandlerToErrorViewAdapter(view, R.string.news_assets_list_load_error_msg))
			}
		}
	}

	override fun onAttachView(view: INewsAssetListView, vararg initData: Any?) {
		this.view = view
	}

	override fun onDetachView() {
		view = null
		disposable?.dispose()
	}

	inner class LoadNewsAssetsSuccessHandler : ICommandSuccessHandler {
		override fun onSuccess(result: Any?) {
			val loadedAssets: NewsAssetListDAO? = result as NewsAssetListDAO?
			view?.refresh(loadedAssets)
		}
	}
}