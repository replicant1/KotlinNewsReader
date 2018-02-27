package com.bailey.rod.kotlinnewsreader;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.bailey.rod.kotlinnewsreader.extensions.ContextExtensionsKt;
import com.bailey.rod.kotlinnewsreader.newsassetlist.view.NewsAssetListActivity;
import com.bailey.rod.kotlinnewsreader.newsassetlist.view.NewsAssetListActivity_;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by rodbailey on 25/2/18.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class NewsAssetsListScreenTest {

	@Rule
	public ActivityTestRule<NewsAssetListActivity_> activityRule = new ActivityTestRule<NewsAssetListActivity_>(NewsAssetListActivity_.class) {
		@Override
		protected Intent getActivityIntent() {
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_MAIN);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			Context appContext = InstrumentationRegistry.getTargetContext();
			try {
				String fileContent = ContextExtensionsKt.assetFileAsString(appContext,
						ParseNewsAssetsTest.Companion.getTEST_JSON_FILE_VALID());
				intent.putExtra(NewsAssetListActivity.Companion.getJSON_STRING_INTENT_EXTRA(), fileContent);
			} catch (IOException iox) {
				System.err.println(iox);
			}
			return intent;
		}
	};

//	@Test
	public void dummyTest() {
		System.out.println("activity=" + activityRule.getActivity());
	}

	//@Test
	public void testFirstItemInListHasCorrectText() {
//		Espresso.onView(ViewMatchers.withId(R.id.rv_news_asset_list)).perform(RecyclerViewActions
//				.actionOnItemAtPosition(0, ViewActions.click()));

		// Check item at position 0 has correct headline
		Espresso.onView(withRecyclerView(R.id.rv_news_asset_list).atPosition(0))
				.check(matches(hasDescendant(withText("Qantas set to pay tax again after strong result"))));

		// Check item at position 0 has correct byline
		Espresso.onView(withRecyclerView(R.id.rv_news_asset_list).atPosition(0))
				.check(matches(hasDescendant(withText("Jemima Whyte "))));

		// Check item at position 0 has correct abstract
		Espresso.onView(withRecyclerView(R.id.rv_news_asset_list).atPosition(0))
				.check(matches(hasDescendant(withText("Qantas Airways is expected to begin paying corporate tax again" +
						" from next year, after reporting a record first half result despite soaring fuel costs."))));

		// Click item at position 3
		//onView(withRecyclerView(R.id.scroll_view).atPosition(3)).perform(click());
	}

	@Test
	public void testOffScreenItemInListHasCorrectText() {
		// Scroll down so that last list item is visible. Once visible, it's text can be inspected.
		Espresso.onView(ViewMatchers.withId(R.id.rv_news_asset_list)).perform(RecyclerViewActions.scrollToPosition
				(13));

		// Check item at position 13 has correct headline
		Espresso.onView(withRecyclerView(R.id.rv_news_asset_list).atPosition(13))
				.check(matches(hasDescendant(withText("Crescent finds buyer for Steel-line, investors ready for " +
						"fundraise"))));
	}

	public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
		return new RecyclerViewMatcher(recyclerViewId);
	}
}
