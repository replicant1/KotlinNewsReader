package com.bailey.rod.kotlinnewsreader

import android.support.test.InstrumentationRegistry
import com.bailey.rod.kotlinnewsreader.extensions.assetFileAsString
import junit.framework.Assert.*
import org.junit.Test
import java.io.IOException

/**
 * Tests for extension functions to the Context class, as defined in [com.bailey.rod.kotlinnewsreader.extensions]
 */
class ContextExtensionsTest {

	@Test
	fun testReadExistingAssetFileAsString() {
		val appContext = InstrumentationRegistry.getTargetContext();
		val fileContent = appContext.assetFileAsString(ParseNewsAssetsTest.TEST_JSON_FILE_VALID)
		assertNotNull(fileContent)
		assert(fileContent.isNotBlank())
		assert(fileContent.isNotEmpty())
	}

	@Test(expected = IOException::class)
	fun testReadNonexistentAssetFileThrowsException() {
		val appContext = InstrumentationRegistry.getContext();
		appContext.assetFileAsString("this_file_doesnt_exist.txt")
	}
}