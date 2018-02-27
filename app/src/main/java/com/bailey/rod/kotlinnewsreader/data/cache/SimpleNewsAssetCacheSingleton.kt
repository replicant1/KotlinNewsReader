package com.bailey.rod.kotlinnewsreader.data.cache

import com.bailey.rod.kotlinnewsreader.app.KotlinNewsReaderApplication
import com.bailey.rod.kotlinnewsreader.data.dao.NewsAssetDAO
import timber.log.Timber

/**
 * Very simple, in-memory cache of NewsAssetDAO instances.
 */
object SimpleNewsAssetCacheSingleton : INewsAssetCache {

	val cache: HashMap<Long, NewsAssetDAO> = HashMap()

	override fun clear() {
		cache.clear()
	}

	override fun put(newsAsset: NewsAssetDAO) {
		if (newsAsset.id != null) {
			cache.put(newsAsset.id, newsAsset)
			sendModifiedEvent()
		}
	}

	override fun putAll(newsAssets: Collection<NewsAssetDAO>) {
		var modified: Boolean = false

		for (newsAsset in newsAssets) {
			if (newsAsset.id != null) {
				cache.put(newsAsset.id, newsAsset)
				modified = true
			}
		}

		if (modified) {
			sendModifiedEvent()
		}
	}

	override fun contains(newsAssetId: Long): Boolean {
		return cache.containsKey(newsAssetId)
	}

	override fun get(newsAssetId: Long): NewsAssetDAO? {
		return cache.get(newsAssetId)
	}

	override fun getAll(): Collection<NewsAssetDAO> {
		return cache.values
	}

	override fun remove(newsAssetId: Long) {
		cache.remove(newsAssetId)
		sendModifiedEvent()
	}

	override fun size(): Int {
		return cache.size
	}

	private fun sendModifiedEvent() {
		Timber.d("Sending cache modified event")
		KotlinNewsReaderApplication.bus.post(NewsAssetCacheModifiedEvent())
	}
}