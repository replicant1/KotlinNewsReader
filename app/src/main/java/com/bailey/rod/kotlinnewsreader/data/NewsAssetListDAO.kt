package com.bailey.rod.kotlinnewsreader.data

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.annotations.SerializedName

/**
 * An immutable representation of a list of [NewsAssetDAO] instances and corresponds to the entire contents of the
 * feed's JSON file.
 */
data class NewsAssetListDAO(

		@SerializedName("assets")
		val assets: List<NewsAssetDAO>?) {

	companion object {

		/**
		 * Parses a JSON string to an equivalent domain object.
		 *
		 * @param jsonString String containing valid JSON
		 * @return Domain object containing the data parsed from [jsonString], or null if parsing was
		 * not possible.
		 */
		@Throws(JsonParseException::class)
		fun parseAssetJson(jsonString: String): NewsAssetListDAO? {
			return Gson().fromJson(jsonString, NewsAssetListDAO::class.java)
		}
	}
}