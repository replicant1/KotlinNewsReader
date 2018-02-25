package com.bailey.rod.kotlinnewsreader.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL

/**
 * Created by rodbailey on 25/2/18.
 */
/**
 *
 */
fun URL.loadImage(): Bitmap? {
	val stream = this.openStream()
	return BitmapFactory.decodeStream(stream)
}

/**
 *
 */
fun URL.loadFile(): String? {
	var bufferedReader: BufferedReader? = null
	var result: String? = null

	try {
		val inStreamReader = InputStreamReader(this.openStream())
		bufferedReader = BufferedReader(inStreamReader)
		var line: String?
		val lineBuffer = StringBuffer()
		var eof: Boolean = false

		while (!eof) {
			line = bufferedReader.readLine()
			if (line == null) {
				eof = true
			} else {
				lineBuffer.append(line)
			}
		}

		bufferedReader.close()
		result = lineBuffer.toString()
	}
	catch (e: Throwable) {
		e.printStackTrace(System.err)
	}
	finally {
		try {
			bufferedReader?.close()
		}
		catch (e: IOException) {
			e.printStackTrace(System.err)
		}
	}

	return result
}