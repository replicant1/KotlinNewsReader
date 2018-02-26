package com.bailey.rod.kotlinnewsreader.data.thumbnail

import com.bailey.rod.kotlinnewsreader.data.RelatedImageDAO

/**
 * Created by rodbailey on 26/2/18.
 */
class SimpleThumbnailSelectionStrategy : IThumbnailSelectionStrategy {

	/**
	 * For set of potential thumbnails P:
	 * <pre>
	 * If there is only one element in P then
	 *   return P[0]
	 * else
	 *    If there is an image x with P[x].type == "afrIndexLead", then
	 *       return the smallest image S with S.id == P[x].id
	 *    else
	 *       return the smallest image in P.
	 * </pre>
	 * Note:
	 * a) "Smallest" is calculated with respect to image size in square pixels, as P.width * P.height (note P.height
	 * can be "0" in the real JSON feed, which I presume is a bug).
	 * b) Ties for "smallest" are broken by selecting the first image in iteration order.
	 *
	 * @param images All images that can be potentially used as a thumbnail for a news asset
	 * @return The member of the [images] collection to be used as the thumbnail, or null if one could not
	 * be selected.
	 */
	override fun selectThumbnail(images: Collection<RelatedImageDAO>): RelatedImageDAO? {
		var result: RelatedImageDAO? = null

		if (images.isEmpty()) {
			result = null
		} else if (images.size == 1) {
			result = images.iterator().next();
		} else
			for (p in images) {
				if ((p.type?.equals("afrIndexLead", ignoreCase = true) == true) && (p.id != null)) {
					result = findSmallestImageWithId(images, p.id)
				}
			}

		if (result == null) {
			result = findSmallestImage(images)
		}

		return result
	}


	private fun findSmallestImageWithId(images: Collection<RelatedImageDAO>, id: Long): RelatedImageDAO? {
		val imagesWithId: HashSet<RelatedImageDAO> = HashSet<RelatedImageDAO>()
		for (image in images) {
			if ((image.id != null) && (image.id == id)) {
				imagesWithId.add(image)
			}
		}

		return findSmallestImage(imagesWithId)
	}

	private fun findSmallestImage(images: Collection<RelatedImageDAO>): RelatedImageDAO? {
		var result: RelatedImageDAO? = null
		for (image in images) {
			if ((result == null) || (size(image) < size(result))) {
				result = image
			}
		}
		return result
	}

	private fun size(image: RelatedImageDAO): Int {
		return (image.width ?: 0).times(image.height ?: 0)
	}
}