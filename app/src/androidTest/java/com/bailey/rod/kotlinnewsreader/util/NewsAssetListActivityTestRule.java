package com.bailey.rod.kotlinnewsreader.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.bailey.rod.kotlinnewsreader.ParseNewsAssetsTest;
import com.bailey.rod.kotlinnewsreader.extensions.ContextExtensionsKt;
import com.bailey.rod.kotlinnewsreader.newsassetlist.view.NewsAssetListActivity;

import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * JUnit test rule that customizes the Intent that launches a NewsAssetListActivity to contain
 * some JSON read from a file in the /assets directory.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class NewsAssetListActivityTestRule<T extends Activity> extends ActivityTestRule<T> {

	private final String jsonFilename;

	public NewsAssetListActivityTestRule(Class<T> activityClass, String jsonFilename) {
		super(activityClass);
		this.jsonFilename = jsonFilename;
	}

	@Override
	protected Intent getActivityIntent() {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_MAIN);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		Context appContext = InstrumentationRegistry.getContext();
		try {
			String fileContent = ContextExtensionsKt.assetFileAsString(appContext, jsonFilename);
			intent.putExtra(NewsAssetListActivity.Companion.getJSON_STRING_INTENT_EXTRA(), fileContent);
		} catch (IOException iox) {
			System.err.println(iox);
		}
		return intent;
	}
}
