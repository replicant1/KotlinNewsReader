package com.bailey.rod.kotlinnewsreader.newsassetlist.view

import com.bailey.rod.kotlinnewsreader.data.dao.NewsAssetDAO

/**
 * Created by rodbailey on 26/2/18.
 */
interface INewsAssetListItemClickListener {
	fun onNewsAssetClick(clickedOn: NewsAssetDAO)
}