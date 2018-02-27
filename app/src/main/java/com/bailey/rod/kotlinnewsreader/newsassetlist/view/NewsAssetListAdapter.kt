package com.bailey.rod.kotlinnewsreader.newsassetlist.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bailey.rod.kotlinnewsreader.R
import com.bailey.rod.kotlinnewsreader.data.dao.NewsAssetDAO

/**
 * Created by rodbailey on 25/2/18.
 */
class NewsAssetListAdapter(private val newsAssets: List<NewsAssetDAO>,
						   private val clickListener: INewsAssetListItemClickListener) :
		RecyclerView.Adapter<NewsAssetListItemViewHolder>() {

	override fun getItemCount(): Int {
		return newsAssets.size
	}

	override fun onBindViewHolder(holder: NewsAssetListItemViewHolder, position: Int) {
		holder.bind(newsAssets[position], clickListener)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAssetListItemViewHolder {
		val v: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_news_asset, parent,
				false) as View
		return NewsAssetListItemViewHolder(v)
	}
}