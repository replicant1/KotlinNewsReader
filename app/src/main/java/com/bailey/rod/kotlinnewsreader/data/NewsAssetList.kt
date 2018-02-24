package com.bailey.rod.kotlinnewsreader.data

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.annotations.SerializedName

/**
 * An immutable representation of a list of [NewsAsset] instances and corresponds to the entire contents of the
 * feed's JSON file.
 */
data class NewsAssetList(
		@SerializedName("id")
		val id: Long?,

		@SerializedName("displayName")
		val name: String?,

		@SerializedName("assets")
		val assets: List<NewsAsset>?) {

	companion object {

		/**
		 * Parses a JSON string to an equivalent domain object.
		 *
		 * @param jsonString String containing valid JSON
		 * @return Domain object containing the data parsed from [jsonString], or null if parsing was
		 * not possible.
		 */
		@Throws(JsonParseException::class)
		fun parseAssetJson(jsonString: String): NewsAssetList? {
			return Gson().fromJson(jsonString, NewsAssetList::class.java)
		}
	}
}