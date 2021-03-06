package com.bailey.rod.kotlinnewsreader.newsassetlist.command

import com.bailey.rod.kotlinnewsreader.app.command.ICommand
import com.bailey.rod.kotlinnewsreader.data.dao.NewsAssetListDAO
import com.bailey.rod.kotlinnewsreader.extensions.loadFile
import java.net.URL

/**
 * Created by rodbailey on 26/2/18.
 */
class LoadNewsAssetListCommand(private val url: String): ICommand<NewsAssetListDAO> {

	override fun execute(): NewsAssetListDAO? {
		val jsonStr: String? = URL(url).loadFile()

		if (jsonStr != null) {
			val allAssets = NewsAssetListDAO.parseAssetJson(jsonStr)
			if ((allAssets != null) && (allAssets.assets != null)) {
				return NewsAssetListDAO.parseAssetJson(jsonStr)
			}
		}

		return null
	}
}