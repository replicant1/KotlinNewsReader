package com.bailey.rod.kotlinnewsreader.newsassetlist.view

import com.bailey.rod.kotlinnewsreader.app.mvp.IView
import com.bailey.rod.kotlinnewsreader.data.dao.NewsAssetDAO

/**
 * Implemented by any party showing a list of news article summaries, where each
 * article summary's data is contained in an instance of NewsAssetDAO.
 * All messages from the corresponding presenter should come via this interface.
 */
interface INewsAssetListView : IView {

	/**
	 * Refresh the view from the data provided
	 */
	fun refresh(listData: Collection<NewsAssetDAO>)
}