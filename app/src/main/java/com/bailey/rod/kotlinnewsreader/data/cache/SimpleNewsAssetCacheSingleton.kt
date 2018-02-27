package com.bailey.rod.kotlinnewsreader.data.cache

import com.bailey.rod.kotlinnewsreader.data.dao.NewsAssetDAO

/**
 * Very simple, in-memory cache of NewsAssetDAO instances.
 */
class SimpleNewsAssetCacheSingleton : INewsAssetCache {

	val cache: HashMap<Long, NewsAssetDAO> = HashMap()

	override fun clear() {
		cache.clear()
	}

	override fun put(newsAsset: NewsAssetDAO) {
		if (newsAsset.id != null) {
			cache.put(newsAsset.id, newsAsset)
		}
	}

	override fun putAll(newsAssets: Collection<NewsAssetDAO>) {
		for (newsAsset in newsAssets) {
			put(newsAsset)
		}
	}

	override fun contains(newsAssetId: Long): Boolean {
		return cache.containsKey(newsAssetId)
	}

	override fun get(newsAssetId: Long): NewsAssetDAO? {
		return cache.get(newsAssetId)
	}

	override fun remove(newsAssetId: Long) {
		cache.remove(newsAssetId)
	}

	override fun size(): Int {
		return cache.size
	}
}