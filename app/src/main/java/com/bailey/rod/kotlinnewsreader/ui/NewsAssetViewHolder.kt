package com.bailey.rod.kotlinnewsreader.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bailey.rod.kotlinnewsreader.R
import com.bailey.rod.kotlinnewsreader.app.GlideApp
import com.bailey.rod.kotlinnewsreader.data.NewsAssetDAO
import com.bailey.rod.kotlinnewsreader.data.thumbnail.SimpleThumbnailSelectionStrategy
import com.bumptech.glide.load.engine.DiskCacheStrategy
import timber.log.Timber


/**
 * Created by rodbailey on 25/2/18.
 */
class NewsAssetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

	@BindView(R.id.tv_news_asset_headline)
	lateinit var headlineTextView: TextView

	@BindView(R.id.tv_news_asset_byline)
	lateinit var bylineTextView: TextView

	@BindView(R.id.tv_news_asset_abstract)
	lateinit var abstractTextView: TextView

	@BindView(R.id.iv_news_asset_thumbnail)
	lateinit var thumbnailImageView: ImageView

	init {
		ButterKnife.bind(this, itemView)
	}

	fun bind(item: NewsAssetDAO, clickListener: INewsAssetClickListener) {
		// Copy item data into text fields for headline, byline and abstract
		headlineTextView.text = item.headline
		bylineTextView.text = item.byline
		abstractTextView.text = item.abstract

		// Set click listener on this news asset - when clicked the user is transitioned to another
		// screen that shows the full news article inside a web view.
		itemView.setOnClickListener { clickListener.onNewsAssetClick(item) }

		// TODO: Clear any current loading even if there is no replacement thumbnail
		val potentialThumbnails = item.relatedImages
		if (potentialThumbnails != null) {
			val thumbnail = SimpleThumbnailSelectionStrategy().selectThumbnail(potentialThumbnails)

			// Cancel any outstanding Glide loads into the image view, then start a load of the thumbnail image
			// for the newly bound item. 60dp to pixels.
			val thumbnailHeightPx = itemView.context.resources.getDimension(R.dimen.asset_list_thumbnail_image_height).toInt()
			val thumbnailWidthPx = itemView.context.resources.getDimension(R.dimen.asset_list_thumbnail_image_width).toInt()

			Timber.d("Loading thumbnail for item ${item.headline} which is at url ${thumbnail?.url}, " +
					"width=${thumbnail?.width}, height=${thumbnail?.height}")

			GlideApp.with(itemView).clear(thumbnailImageView)
			GlideApp.with(itemView)
					.load(thumbnail?.url)
					.centerCrop()
					.override(thumbnailWidthPx, thumbnailHeightPx)
					.diskCacheStrategy(DiskCacheStrategy.ALL)
					.into(thumbnailImageView)

		}
	}
}