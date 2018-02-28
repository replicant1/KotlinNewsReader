package com.bailey.rod.kotlinnewsreader.util;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * This class comes from here:
 * https://stackoverflow.com/questions/36399787/how-to-count-recyclerview-items-with-espresso
 * It checks the number of items in a RecyclerView.
 */
public class RecyclerViewItemCountAssertion implements ViewAssertion {
	private final int expectedCount;

	public RecyclerViewItemCountAssertion(int expectedCount) {
		this.expectedCount = expectedCount;
	}

	@Override
	public void check(View view, NoMatchingViewException noViewFoundException) {
		if (noViewFoundException != null) {
			throw noViewFoundException;
		}

		RecyclerView recyclerView = (RecyclerView) view;
		RecyclerView.Adapter adapter = recyclerView.getAdapter();
		assertThat(adapter.getItemCount(), is(expectedCount));
	}

}
