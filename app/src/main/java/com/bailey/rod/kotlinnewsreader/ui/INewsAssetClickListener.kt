package com.bailey.rod.kotlinnewsreader.ui

import com.bailey.rod.kotlinnewsreader.data.NewsAssetDAO

/**
 * Created by rodbailey on 26/2/18.
 */
interface INewsAssetClickListener {
	fun onNewsAssetClick(clickedOn: NewsAssetDAO)
}