package com.bailey.rod.kotlinnewsreader;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
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
				intent.putExtra(NewsAssetListActivity.Companion.getJSON_STRING_EXTRA(), fileContent);
			} catch (IOException iox) {
				System.err.println(iox);
			}
			return intent;
		}
	};

	@Test
	public void dummyTest() {
		System.out.println("activity=" + activityRule.getActivity());
	}
}
