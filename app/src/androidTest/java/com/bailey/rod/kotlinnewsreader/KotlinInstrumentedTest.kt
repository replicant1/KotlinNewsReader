package com.bailey.rod.kotlinnewsreader

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.bailey.rod.kotlinnewsreader.data.NewsAssetList
import com.bailey.rod.kotlinnewsreader.extensions.assetFileAsString

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class KotlinInstrumentedTest {
	@Test
	fun useAppContext() {
		// Context of the app under test.
		val appContext = InstrumentationRegistry.getTargetContext()
		assertEquals("com.bailey.rod.kotlinnewsreader", appContext.packageName)
	}

	@Test
	fun testReadJsonFileToString() {
		val appContext = InstrumentationRegistry.getContext()
		val jsonString = appContext.assetFileAsString(TEST_JSON_FILE)
		assertTrue(jsonString.isNotBlank())
	}

	@Test
	fun testParseJsonFile() {
		val appContext = InstrumentationRegistry.getContext()
		val jsonString = appContext.assetFileAsString(TEST_JSON_FILE)
		val assetList: NewsAssetList? = NewsAssetList.parseAssetJson(jsonString)
		assertNotNull(assetList)
		assertNotNull(assetList?.assets)
		assertTrue(assetList?.assets?.isNotEmpty() ?: false)
		assertEquals(NUMBER_OF_ASSETS, assetList?.assets?.size)
		System.out.println("Number of assets is " + assetList?.assets?.size)
		System.out.println("asset list is " + assetList)
	}

	companion object {
		val TEST_JSON_FILE: String = "fairfax_json.txt"
		val NUMBER_OF_ASSETS: Int = 14
	}
}
