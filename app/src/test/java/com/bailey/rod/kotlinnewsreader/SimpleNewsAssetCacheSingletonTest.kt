package com.bailey.rod.kotlinnewsreader

import com.bailey.rod.kotlinnewsreader.data.cache.SimpleNewsAssetCacheSingleton
import com.bailey.rod.kotlinnewsreader.data.dao.NewsAssetDAO
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Instrumented tests for the SimpleNewsAssetCacheSingleton class.
 */
class SimpleNewsAssetCacheSingletonTest {

	companion object {
		val ASSET_1234_ID = 1234L
		val ASSET_5678_ID = 5678L
		val ASSET_1234 = NewsAssetDAO(ASSET_1234_ID, null, null, null, null, null, null)
		val ASSET_5678 = NewsAssetDAO(ASSET_5678_ID, null, null, null, null, null, null)
		val ASSET_WITH_NULL_ID = NewsAssetDAO(null, null, null, null, null, null, null)
	}

	private var cache: SimpleNewsAssetCacheSingleton = SimpleNewsAssetCacheSingleton


	@Before
	fun clearCacheBeforeEachTest() {
		cache.testMode = true
		cache.clear()
	}

	@Test
	fun testCacheCreatedWithDefaultConstructorIsEmpty() {
		assertEquals(0, cache.size())
	}

	@Test
	fun testPutThenGetReturnsSameObject() {
		// Add asset to cache
		cache.put(ASSET_1234)
		assertEquals(1, cache.size())

		// Retrieve asset
		val assetAsRetrieved: NewsAssetDAO? = cache.get(ASSET_1234_ID)
		assertNotNull(assetAsRetrieved)
		assertEquals(assetAsRetrieved, ASSET_1234)
	}

	@Test
	fun testPutAssetWithNullIdHasNoEffect() {
		assertEquals(0, cache.size())
		cache.put(ASSET_WITH_NULL_ID)
		assertEquals(0, cache.size())
	}

	@Test
	fun testClearAfterPutLeavesCacheEmpty() {
		cache.put(ASSET_1234)
		assertTrue(cache.contains(ASSET_1234_ID))
		cache.clear()
		assertEquals(0, cache.size())
	}

	@Test
	fun testRemoveExisting() {
		cache.put(ASSET_1234)
		assertEquals(1, cache.size())
		cache.remove(ASSET_1234_ID)
		assertEquals(0, cache.size())
	}

	@Test
	fun testRemoveNonExistingHasNoEffect() {
		cache.remove(0L)
		assertEquals(0, cache.size())
	}

	@Test
	fun testContains() {
		cache.put(ASSET_1234)
		assertTrue(cache.contains(ASSET_1234_ID))

		cache.remove(ASSET_1234_ID)
		assertFalse(cache.contains(ASSET_1234_ID))
	}

	@Test
	fun testPutAll() {
		val assets: List<NewsAssetDAO> = listOf(ASSET_1234, ASSET_5678)
		cache.putAll(assets)
		assertEquals(2, cache.size())
	}
}