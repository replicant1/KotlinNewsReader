package com.bailey.rod.kotlinnewsreader;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.bailey.rod.kotlinnewsreader.newsassetlist.view.NewsAssetListActivity_;
import com.bailey.rod.kotlinnewsreader.util.NewsAssetListActivityTestRule;
import com.bailey.rod.kotlinnewsreader.util.RecyclerViewItemCountAssertion;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Instrumented test for the NewsAssetListActivity that launches it with an Intent containing literal
 * JSON specifying 0 news assets.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ZeroNewsAssetsListScreenTest {

	@Rule
	public NewsAssetListActivityTestRule<NewsAssetListActivity_> activityRule =
			new NewsAssetListActivityTestRule(NewsAssetListActivity_.class,
					ParseNewsAssetsTest.Companion.getTEST_JSON_FILE_VALID_0_ASSETS());

	@Test
	public void testEmptyViewIsShowing() {
		// "Empty" message is showing
		Espresso.onView(ViewMatchers.withId(R.id.rl_view_empty)).check(
				ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

		// Asset list is hidden
		Espresso.onView(ViewMatchers.withId(R.id.rv_news_asset_list)).check(
				ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
	}
}
