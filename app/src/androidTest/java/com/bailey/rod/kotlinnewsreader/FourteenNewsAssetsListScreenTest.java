package com.bailey.rod.kotlinnewsreader;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.bailey.rod.kotlinnewsreader.newsassetlist.view.NewsAssetListActivity_;
import com.bailey.rod.kotlinnewsreader.util.NewsAssetListActivityTestRule;
import com.bailey.rod.kotlinnewsreader.util.RecyclerViewItemCountAssertion;
import com.bailey.rod.kotlinnewsreader.util.RecyclerViewMatcher;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test for the NewsAssetListActivity that launches it with an Intent containing literal
 * JSON specifying 14 news assets.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class FourteenNewsAssetsListScreenTest {

	@Rule
	public NewsAssetListActivityTestRule<NewsAssetListActivity_> activityRule =
			new NewsAssetListActivityTestRule(NewsAssetListActivity_.class,
					ParseNewsAssetsTest.Companion.getTEST_JSON_FILE_VALID_14_ASSETS());

	//@Test
	public void testThereAre14ItemsInTheList() {
		Espresso.onView(ViewMatchers.withId(R.id.rv_news_asset_list)).check(new RecyclerViewItemCountAssertion(14));
	}

	@Test
	public void testFirstItemInListHasCorrectText() {
		// Check item at position 0 has correct headline
		Espresso.onView(RecyclerViewMatcher.withRecyclerView(R.id.rv_news_asset_list).atPosition(0))
				.check(matches(hasDescendant(withText("Markets Live: Miners haul ASX higher"))));

		// Check item at position 0 has correct byline
		Espresso.onView(RecyclerViewMatcher.withRecyclerView(R.id.rv_news_asset_list).atPosition(0))
				.check(matches(hasDescendant(withText("Sarah Turner"))));

		// Check item at position 0 has correct abstract
		Espresso.onView(RecyclerViewMatcher.withRecyclerView(R.id.rv_news_asset_list).atPosition(0))
				.check(matches(hasDescendant(withText("Australian shares surged on Friday to cement a third straight " +
						"session of gains and a weekly advance."))));
	}

	@Test
	public void testLastItemInListHasCorrectText() {
		// The last item in the list is off-screen.
		// Scroll down so that last list item is visible. Once visible, it's text can be inspected.
		Espresso.onView(ViewMatchers.withId(R.id.rv_news_asset_list)).perform(RecyclerViewActions.scrollToPosition
				(13));

		// Check item at position 13 has correct headline
		Espresso.onView(RecyclerViewMatcher.withRecyclerView(R.id.rv_news_asset_list).atPosition(13))
				.check(matches(hasDescendant(withText("Melbourne Food and Wine Festival 2018: your guide to what's cooking"))));
	}


}
