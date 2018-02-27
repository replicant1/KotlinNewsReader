package com.bailey.rod.kotlinnewsreader.data.dao

import com.google.gson.annotations.SerializedName

/**
 * An immutable representation of an image that relates in some way to an [NewsAssetDAO]
 */
data class RelatedImageDAO(
		@SerializedName("id")
		val id: Long?,

		@SerializedName("url")
		val url: String?,

		@SerializedName("type")
		val type: String?,

		@SerializedName("width")
		val width: Int?,

		@SerializedName("height")
		val height: Int?) {

}