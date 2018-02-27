package com.bailey.rod.kotlinnewsreader

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.bailey.rod.kotlinnewsreader.data.dao.NewsAssetDAO
import com.bailey.rod.kotlinnewsreader.data.dao.NewsAssetListDAO
import com.bailey.rod.kotlinnewsreader.data.dao.RelatedImageDAO
import com.bailey.rod.kotlinnewsreader.extensions.assetFileAsString
import com.google.gson.JsonParseException
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, to test parsing of JSON feed into [NewsAssetListDAO]
 */
@RunWith(AndroidJUnit4::class)
class ParseNewsAssetsTest {
	@Test
	fun useAppContext() {
		// Context of the app under test.
		val appContext = InstrumentationRegistry.getTargetContext()
		assertEquals("com.bailey.rod.kotlinnewsreader", appContext.packageName)
	}

	@Test
	fun testReadJsonFileToString() {
		val jsonString = readJsonFileToString()
		assertTrue(jsonString.isNotBlank())
	}

	@Test
	fun testParseJsonFileToCorrectNumberOfNewsAssets() {
		val assetList: NewsAssetListDAO? = parseJsonFileToAssetList(TEST_JSON_FILE_VALID)
		assertNotNull(assetList)
		assertNotNull(assetList?.assets)
		assertTrue(assetList?.assets?.isNotEmpty() ?: false)
		assertEquals(14, assetList?.assets?.size)
	}

	@Test
	fun testParseJsonFileFirstAsset() {
		val assetList: NewsAssetListDAO? = parseJsonFileToAssetList(TEST_JSON_FILE_VALID)
		val firstAsset: NewsAssetDAO? = assetList?.assets?.first()
		assertNotNull(firstAsset)
		assertEquals(1029434891L, firstAsset?.id)
		assertEquals("Qantas set to pay tax again after strong result", firstAsset?.headline)
		assertEquals("Qantas Airways is expected to begin paying corporate tax again from next year, after reporting " +
				"a record first half result despite soaring fuel costs.", firstAsset?.abstract)
		assertEquals("Jemima Whyte ", firstAsset?.byline)
	}

	@Test
	fun testParseJsonFileFirstAssetRelatedImages() {
		val assetList: NewsAssetListDAO? = parseJsonFileToAssetList(TEST_JSON_FILE_VALID)
		val firstAsset: NewsAssetDAO? = assetList?.assets?.first()
		val relatedImages: List<RelatedImageDAO>? = firstAsset?.relatedImages
		assertNotNull(relatedImages)
		assertEquals(4, relatedImages?.size)
	}

	@Test
	fun testParseJsonFileFirstAssetFirstRelatedImage() {
		val assetList: NewsAssetListDAO? = parseJsonFileToAssetList(TEST_JSON_FILE_VALID)
		val firstAsset: NewsAssetDAO? = assetList?.assets?.first()
		val relatedImages: List<RelatedImageDAO>? = firstAsset?.relatedImages
		val firstRelatedImage: RelatedImageDAO? = relatedImages?.first()

		assertEquals(1021253707L, firstRelatedImage?.id)
		assertEquals("https://www.fairfaxstatic.com.au/content/dam/images/g/w/1/0/e/j/image.related.afrArticleInline" +
				".620x0.h0wd1n.13zzqx.png/1519356336345.jpg", firstRelatedImage?.url)
		assertEquals(620, firstRelatedImage?.width)
		assertEquals(0, firstRelatedImage?.height)
	}

	@Test(expected = JsonParseException::class)
	fun testParseInvalidJsonFileThrowsException() {
		val assetList: NewsAssetListDAO? = parseJsonFileToAssetList(TEST_JSON_FILE_INVALID)
		println("assetList = $assetList")
	}

	private fun parseJsonFileToAssetList(jsonFilePath: String): NewsAssetListDAO? {
		val appContext = InstrumentationRegistry.getTargetContext()
		val jsonString = appContext.assetFileAsString(jsonFilePath)
		return NewsAssetListDAO.parseAssetJson(jsonString)
	}

	private fun readJsonFileToString(): String {
		val appContext = InstrumentationRegistry.getTargetContext()
		return appContext.assetFileAsString(TEST_JSON_FILE_VALID)
	}

	companion object {
		val TEST_JSON_FILE_VALID: String = "valid_json_23FEB2018.txt"
		val TEST_JSON_FILE_INVALID: String = "invalid_json_truncated.txt"
	}
}
