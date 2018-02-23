package com.bailey.rod.kotlinnewsreader.data

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**
 * Created by rodbailey on 23/2/18.
 */
data class NewsAssetList(
		@SerializedName("id")
		val id: Long?,

		@SerializedName("displayName")
		val name: String?,

		@SerializedName("assets")
		val assets: List<NewsAsset>?) {

	companion object {
		fun parseAssetJson(jsonString: String): NewsAssetList? {
			return Gson().fromJson(jsonString, NewsAssetList::class.java)
		}
	}
}