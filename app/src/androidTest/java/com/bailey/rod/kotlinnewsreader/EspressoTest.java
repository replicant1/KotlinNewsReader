package com.bailey.rod.kotlinnewsreader;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.bailey.rod.kotlinnewsreader.ui.MainActivity_;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by rodbailey on 25/2/18.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest {

	@Rule
	public ActivityTestRule<MainActivity_> activityRule = new ActivityTestRule<MainActivity_>(MainActivity_.class);

	@Test
	public void dummyTest() {
		System.out.println("activity=" + activityRule.getActivity());
	}
}
