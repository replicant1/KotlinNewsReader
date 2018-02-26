package com.bailey.rod.kotlinnewsreader

import com.bailey.rod.kotlinnewsreader.data.RelatedImageDAO
import com.bailey.rod.kotlinnewsreader.data.thumbnail.IThumbnailSelectionStrategy
import com.bailey.rod.kotlinnewsreader.data.thumbnail.SimpleThumbnailSelectionStrategy
import junit.framework.Assert.assertTrue
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Created by rodbailey on 26/2/18.
 */
class SimpleThumbnailSelectionStrategyTest {

	val images: HashSet<RelatedImageDAO> = HashSet<RelatedImageDAO>()

	val strategy: IThumbnailSelectionStrategy = SimpleThumbnailSelectionStrategy()

	@Before
	fun beforeEachTest() {
		images.clear()
	}

	companion object {
		val IMAGE_ID_1234_SIZE_10000_TYPE_INDEX = RelatedImageDAO(
				id = 1234,
				url = "http://image_1234_a.png",
				type = "afrIndexLead",
				width = 100,
				height = 100)
		val IMAGE_ID_1234_SIZE_0_TYPE_INDEX = RelatedImageDAO(
				id = 1234,
				url = "http://image_1234_c.png",
				type = "afrIndexLead",
				width = 520,
				height = 0)
		val IMAGE_ID_1234_SIZE_3000_TYPE_INLINE = RelatedImageDAO(
				id = 1234,
				url = "http://image_1234_b.png",
				type = "afrArticleInLine",
				width = 30,
				height = 100)
		val IMAGE_ID_5678_SIZE_2500_TYPE_INLINE = RelatedImageDAO(
				id = 5678,
				url = "http://image_5678_a.png",
				type = "afrArticleInLine",
				width = 50,
				height = 50)
		val IMAGE_ID_9999_SIZE_10000_TYPE_LEAD = RelatedImageDAO(
				id = 9999,
				url = "http://image_9999_a.png",
				type = "articleLeadWide",
				width = 100,
				height = 100)
		val IMAGE_ID_8888_SIZE_400_TYPE_INLINE = RelatedImageDAO(
				id = 8888,
				url = "http://image_8888_a.png",
				type = "articleInLine",
				width = 20,
				height = 20)
		val IMAGE_ID_7777_SIZE_0_TYPE_INLINE = RelatedImageDAO(
				id = 7777,
				url = "http://image_7777_a.png",
				type = "articleInLine",
				width = 640,
				height = 0)
		val IMAGE_ID_6666_SIZE_0_TYPE_INLINE = RelatedImageDAO(
				id = 6666,
				url = "http://image_6666_a.png",
				type = "articleInLine",
				width = 530,
				height = 0)
	}

	@Test
	fun testEmptySetReturnsNull() {
		assertTrue(images.isEmpty())
		assertNull(strategy.selectThumbnail(images))
	}

	@Test
	fun testSingletonIndexIsSelected() {
		images.add(IMAGE_ID_1234_SIZE_10000_TYPE_INDEX)
		assertEquals(IMAGE_ID_1234_SIZE_10000_TYPE_INDEX, strategy.selectThumbnail(images))
	}

	@Test
	fun testSingletonInLineIsSelected() {
		images.add(IMAGE_ID_1234_SIZE_3000_TYPE_INLINE)
		assertEquals(IMAGE_ID_1234_SIZE_3000_TYPE_INLINE, strategy.selectThumbnail(images))
	}

	@Test
	fun testIndexIsSelectedEvenWhenNotSmallest() {
		images.add(IMAGE_ID_1234_SIZE_10000_TYPE_INDEX)
		images.add(IMAGE_ID_6666_SIZE_0_TYPE_INLINE)
		assertEquals(IMAGE_ID_1234_SIZE_10000_TYPE_INDEX, strategy.selectThumbnail(images))
	}

	@Test
	fun testSmallestIndexIsSelected() {
		images.add(IMAGE_ID_1234_SIZE_10000_TYPE_INDEX)
		images.add(IMAGE_ID_1234_SIZE_3000_TYPE_INLINE)
		assertEquals(IMAGE_ID_1234_SIZE_3000_TYPE_INLINE, strategy.selectThumbnail(images))
	}

	@Test
	fun testIndexIsSelectedWhenSizeIsZero() {
		images.add(IMAGE_ID_1234_SIZE_0_TYPE_INDEX)
		images.add(IMAGE_ID_6666_SIZE_0_TYPE_INLINE)
		images.add(IMAGE_ID_7777_SIZE_0_TYPE_INLINE)
		assertEquals(IMAGE_ID_1234_SIZE_0_TYPE_INDEX, strategy.selectThumbnail(images))
	}
}