package com.bailey.rod.kotlinnewsreader.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.bailey.rod.kotlinnewsreader.R
import com.bailey.rod.kotlinnewsreader.data.NewsAssetDAO

/**
 * Created by rodbailey on 25/2/18.
 */
class NewsAssetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

	fun bind(item: NewsAssetDAO) {
		val headlineTextView: TextView = itemView.findViewById<TextView>(R.id.tv_news_asset_headline)
		val bylineTextView: TextView = itemView.findViewById<TextView>(R.id.tv_news_asset_byline)
		val abstractTextView: TextView = itemView.findViewById<TextView>(R.id.tv_news_asset_abstract)
		headlineTextView.text = item.headline
		bylineTextView.text = item.byline
		abstractTextView.text = item.abstract
	}
}