package com.bailey.rod.kotlinnewsreader

import com.bailey.rod.kotlinnewsreader.data.dao.NewsAssetDAO
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * Created by rodbailey on 28/2/18.
 */
class NewsAssetDAOTest {

	companion object {
		val ASSET_OLDEST_ID = 1234L
		val ASSET_OLDEST_TIMESTAMP = GregorianCalendar(2014, Calendar.FEBRUARY, 11).timeInMillis
		val ASSET_OLDEST = NewsAssetDAO(
				id = ASSET_OLDEST_ID,
				headline = null,
				abstract = null,
				byline = null,
				timeStamp = ASSET_OLDEST_TIMESTAMP,
				url = null,
				relatedImages = null)

		val ASSET_MIDDLE_ID = 5678L
		val ASSET_MIDDLE_TIMESTAMP = GregorianCalendar(2014, Calendar.MARCH, 10).timeInMillis
		val ASSET_MIDDLE = NewsAssetDAO(
				id = ASSET_MIDDLE_ID,
				headline = null,
				abstract = null,
				byline = null,
				timeStamp = ASSET_MIDDLE_TIMESTAMP,
				url = null,
				relatedImages = null)

		val ASSET_NEWEST_ID = 6789L
		val ASSET_NEWEST_TIMESTAMP = GregorianCalendar(2015, Calendar.JANUARY, 9).timeInMillis
		val ASSET_NEWEST = NewsAssetDAO(
				id = ASSET_NEWEST_ID,
				headline =  null,
				abstract = null,
				byline = null,
				timeStamp = ASSET_NEWEST_TIMESTAMP,
				url = null,
				relatedImages = null)

		val ASSET_UNKNOWN_ID = 9999L
		val ASSET_UNKNOWN_TIMESTAMP = null
		val ASSET_UNKNOWN = NewsAssetDAO(
				id= ASSET_UNKNOWN_ID,
				headline = null,
				abstract = null,
				byline = null,
				timeStamp = ASSET_UNKNOWN_TIMESTAMP,
				url = null,
				relatedImages = null)
	}

	val list: LinkedList<NewsAssetDAO> = LinkedList<NewsAssetDAO>()

	@Before
	fun beforeEachTest() {
		list.clear()
	}

	@Test
	fun testSortSingletonListIsSame() {
		list.add(ASSET_OLDEST)
		assertEquals(1, list.size)
		Collections.sort(list)
		assertEquals(1, list.size)
		assertEquals(list[0], ASSET_OLDEST)
	}

	@Test
	fun testSortTwoAssetListIsNewestToOldest() {
		list.addAll(setOf(ASSET_OLDEST, ASSET_MIDDLE))
		Collections.sort(list)
		assertEquals(2, list.size)
		assertEquals(list[0], ASSET_MIDDLE)
		assertEquals(list[1], ASSET_OLDEST)
	}

	@Test
	fun testSortThreeAssetListIsNewestToOldest() {
		list.addAll(setOf(ASSET_OLDEST, ASSET_MIDDLE, ASSET_NEWEST))
		Collections.sort(list)
		assertEquals(3, list.size)
		assertEquals(ASSET_NEWEST, list[0])
		assertEquals(ASSET_MIDDLE, list[1])
		assertEquals(ASSET_OLDEST, list[2])
	}

	@Test
	fun testSortSameAssetTwiceIsUnchanged() {
		list.addAll(listOf(ASSET_NEWEST, ASSET_NEWEST))
		Collections.sort(list)
		assertEquals(2, list.size)
		assertEquals(ASSET_NEWEST, list[0])
		assertEquals(ASSET_NEWEST, list[1])
	}

	@Test
	fun testWithNullTimeStampDoesntFail() {
		list.addAll(listOf(ASSET_OLDEST, ASSET_UNKNOWN))
		Collections.sort(list)
		assertEquals(2, list.size)
		assertTrue(list.contains(ASSET_OLDEST))
		assertTrue(list.contains(ASSET_UNKNOWN))
	}
}