package com.bailey.rod.kotlinnewsreader.data

import com.google.gson.annotations.SerializedName

/**
 * An immutable representation of a single news article.
 */
data class NewsAsset(
		@SerializedName("id")
		val id: Long?,

		@SerializedName("headline")
		val headline: String?,

		@SerializedName("theAbstract")
		val abstract: String?,

		@SerializedName("byLine")
		val byline: String?,

		@SerializedName("timeStamp")
		val timeStamp: Long?,

		@SerializedName("relatedImages")
		val relatedImages: List<RelatedImage>?)
