package com.bailey.rod.kotlinnewsreader.data

import com.google.gson.annotations.SerializedName

/**
 * An immutable representation of an image that relates in some way to an [NewsAsset]
 */
data class RelatedImage(
		@SerializedName("id")
		val id: Long?,

		@SerializedName("url")
		val url: String?,

		@SerializedName("width")
		val width: Int?,

		@SerializedName("height")
		val height: Int?) {

}