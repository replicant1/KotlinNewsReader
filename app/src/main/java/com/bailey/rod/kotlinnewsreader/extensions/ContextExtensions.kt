package com.bailey.rod.kotlinnewsreader.extensions

import android.content.Context
import java.io.BufferedReader
import java.io.IOException

/**
 * Loads a given file from the /assets folder for this app.
 * @param context App context
 * @param assetFileName Simple file name to be loaded.
 * @return Contents of the asset file in String form
 */
@Throws(IOException::class)
fun Context.assetFileAsString(fileName: String): String {
	val bufferedReader: BufferedReader? = null

	try {
		val inputStream = this.assets.open(fileName)
		val size = inputStream.available()
		val buffer = ByteArray(size)
		inputStream.read(buffer)
		inputStream.close()

		return String(buffer)
	} finally {
		bufferedReader?.close()
	}
}
