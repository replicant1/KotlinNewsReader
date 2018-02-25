package com.bailey.rod.kotlinnewsreader.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.bailey.rod.kotlinnewsreader.R
import com.bailey.rod.kotlinnewsreader.data.NewsAssetDAO

/**
 * Created by rodbailey on 25/2/18.
 */
class NewsAssetListAdapter(val newsAssets: List<NewsAssetDAO>) : RecyclerView.Adapter<NewsAssetViewHolder>() {

	override fun getItemCount(): Int {
		return newsAssets.size
	}

	override fun onBindViewHolder(holder: NewsAssetViewHolder, position: Int) {
		holder.bind(newsAssets[position])
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAssetViewHolder {
		val v: LinearLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item_news_asset, parent,
				false) as LinearLayout
		return NewsAssetViewHolder(v)
	}
}