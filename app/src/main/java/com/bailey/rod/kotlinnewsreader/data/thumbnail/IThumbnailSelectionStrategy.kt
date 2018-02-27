package com.bailey.rod.kotlinnewsreader.data.thumbnail

import com.bailey.rod.kotlinnewsreader.data.dao.RelatedImageDAO

/**
 * A method for selecting which of the images related to a given news asset should be used for the thumbnail image
 * beside the asset in the news asset list.
 */
interface IThumbnailSelectionStrategy {

	/**
	 * @return The related image that is most suitable to use as a thumbnail, or null if no suitable image found
	 */
	fun selectThumbnail(images: Collection<RelatedImageDAO>) : RelatedImageDAO?
}