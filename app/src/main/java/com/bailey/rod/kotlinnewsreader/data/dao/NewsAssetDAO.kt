package com.bailey.rod.kotlinnewsreader.data.dao

import com.google.gson.annotations.SerializedName

/**
 * An immutable representation of a single news article.
 */
data class NewsAssetDAO(
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

		@SerializedName("url")
		val url: String?,

		@SerializedName("relatedImages")
		val relatedImages: List<RelatedImageDAO>?) : Comparable<NewsAssetDAO> {

	/**
	 * Default sort order is most recent 'timeStamp' first
	 */
	override fun compareTo(other: NewsAssetDAO): Int {
		val thisTimeStamp: Long = timeStamp ?: 0
		val otherTimeStamp: Long = other.timeStamp ?: 0
		val diff:Long = (thisTimeStamp - otherTimeStamp)

		return when  {
			diff < 0 -> 1
			diff > 0 -> -1
			else -> 0
		}
	}
}
