package com.bailey.rod.kotlinnewsreader.newsassetlist.pres

import android.content.Context
import android.content.Intent
import com.bailey.rod.kotlinnewsreader.app.mvp.IPresenter
import com.bailey.rod.kotlinnewsreader.newsassetlist.view.INewsAssetListView

/**
 * Implemented by any party presenting an INewsAssetListView. All messages from the view
 * to the presenter should come via this interface.
 */
interface INewsAssetListPresenter : IPresenter<INewsAssetListView> {

	/**
	 * Extract the data for the list of news article summaries from the given Intent.
	 * If the intent contains a URI, the data is asynchronously loaded from that URI
	 * and later communicated to the view. If the intent contains literal JSON in a String
	 * extra named NewsAssetListActivity.JSON_STRING_INTENT_EXTRA, then the list data
	 * is parsed out of that JSON.
	 */
	fun loadNewsAssetList(intent: Intent)

}