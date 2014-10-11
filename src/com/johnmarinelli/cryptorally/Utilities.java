package com.johnmarinelli.cryptorally;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

/*
 * fake static class because Java, put simply, sucks
 */
public final class Utilities {
	private Utilities(){};
	
	public static final int ASCII_CHAR_MIN = 65;
	public static final int ASCII_CHAR_MAX = 90;
	
	public static void addViewToLayout(View v, LinearLayout l) {
		v.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 
				ViewGroup.LayoutParams.WRAP_CONTENT));
		l.addView(v);
	}

}
