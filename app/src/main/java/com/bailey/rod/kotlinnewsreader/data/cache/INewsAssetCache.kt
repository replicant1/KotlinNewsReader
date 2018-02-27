package com.bailey.rod.kotlinnewsreader.data.cache

import com.bailey.rod.kotlinnewsreader.data.dao.NewsAssetDAO

/**
 * A cache of NewsAssetDAO instances. Implementors are free to choose the basis for caching (memory, disk or both).
 * They may also decide if cached instances should be evicted after a time (this is not a requirement).
 */
interface INewsAssetCache {

	/**
	 * Empties the cache. After this, [size] == 0.
	 */
	fun clear()

	/**
	 * @param newsAsset Asset to be added into the cache. If cache already contains an asset with the same id, the
	 * old asset is replaced with this new one.
	 */
	fun put(newsAsset: NewsAssetDAO)

	/**
	 * Calling this method is equivalent to calling [put] on each element in [newsAssets]
	 * @param newsAssets A collection of assets to be added into the cache.
	 */
	fun putAll(newsAssets: Collection<NewsAssetDAO>)

	/**
	 * @return True if the cache currently contains
	 */
	fun contains(newsAssetId: Long): Boolean

	/**
	 * @return The cache entry with the given key, or null if not found.
	 */
	fun get(newsAssetId: Long): NewsAssetDAO?

	/**
	 * @param newsAssetId of the news asset to remove from the cache. If no such asset is found, has no effect.
	 */
	fun remove(newsAssetId: Long)

	/**
	 * @return The number of news assets stored in this cache
	 */
	fun size(): Int
}