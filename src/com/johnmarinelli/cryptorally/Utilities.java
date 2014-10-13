package com.johnmarinelli.cryptorally;

import java.math.BigInteger;

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
	public static final int ALPHABET_LENGTH = 26;
	
	public static void addViewsToLayout(LinearLayout l, View... vs) {
		for (View v : vs) {
			v.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 
				ViewGroup.LayoutParams.WRAP_CONTENT));
			l.addView(v);
		}
	}
	
	public static int greatestCommonDivisor(int a, int b)
	{
		int remainder = a % b;
		int quotient = a / b;
		
		if(remainder == 0){
			return b;
		}
		else{
			return greatestCommonDivisor(b, remainder);
		}
	}
	
	public static int getModularInv(int a, int n)
	{
		BigInteger res = BigInteger.valueOf(a).modInverse(BigInteger.valueOf(n));
		
		return res.intValue();
	}

}
