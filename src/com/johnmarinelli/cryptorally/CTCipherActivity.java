package com.johnmarinelli.cryptorally;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class CTCipherActivity extends CipherActivity {
	
	EditText mEncryptionKey;

	@SuppressLint("NewApi")
	private String decrypt(String input, String key) {
		String decrypted = "";
		key = Utilities.sanitizeString(key);
		int keyLength = key.length();
		ArrayList<Double> keyArrayList = new ArrayList<Double>(keyLength); 
				
		/*
		 * convert key into digital values
		 * if we have a duplicate character, add a small increment to it
		 * so that the map stays in order
		 */
		for(char c : key.toCharArray()) {
			double charVal = c;
			if(keyArrayList.contains(charVal)) charVal += .1;
			keyArrayList.add(charVal);
		}
		
		input = Utilities.sanitizeString(input);
		
		int inputLength = input.length();
		
		Log.i("keylength", Integer.toString(keyLength));
		Log.i("inlength", Integer.toString(inputLength));
		
		int matrixHeight = (int) Math.ceil(((double)inputLength / (double)keyLength));
				
		/* TreeMaps are naturally in ascending order */
		TreeMap<Double, ArrayList<Character>> keyMap = new TreeMap<Double, ArrayList<Character>>();
		int inputItr = 0;
		
		for(int i = 0; i < keyLength; ++i) {	
			ArrayList<Character> col = new ArrayList<Character>(matrixHeight);
			for(int j = 0; j < matrixHeight; ++j) col.add(' ');
			keyMap.put(keyArrayList.get(i), col);
		}
		
		/* fill the columns with markers if it doesn't match height */
		int diff = Math.abs((matrixHeight * keyLength) - inputLength);
		Log.i("diff", Integer.toString(diff));
		
		for(int i = keyLength-1; i > keyLength-diff-1; --i) {
			Log.i("i", Integer.toString(i));
			keyMap.get(Double.valueOf((double)key.charAt(i))).set(matrixHeight-1, '-');
		}
		
		/* populate keyMap with chunks from input */
		for(Map.Entry<Double, ArrayList<Character>> column : keyMap.entrySet()) {
			ArrayList<Character> chunk = column.getValue();
			
			/* put matrixHeight number of characters from input into column */
			for(int j = 0; j < matrixHeight; ++j) {
				if(inputItr < inputLength && chunk.get(j) != '-') {
					chunk.set(j, input.charAt(inputItr++));
				}
			}
						
		    keyMap.put(column.getKey(), chunk);
		}
		
		Log.i("keymap", keyMap.toString());
		
		/* 
		 * with our 2d array in original order we read right to left
		 * top to bottom to get plaintext
		 */
		for(int colItr = 0; colItr < matrixHeight; ++colItr) {
			/* concatenate values based on original key */
			for(double c : keyArrayList) {
				Log.i("ind col", keyMap.get(c).toString());
				decrypted += keyMap.get(c).get(colItr);
			}
		}
				
		return decrypted;
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* add edit text to layout */
		mEncryptionKey = new EditText(this.getBaseContext());
		mEncryptionKey.setTextColor(Color.BLACK);
		
		Utilities.addViewsToLayout((LinearLayout) findViewById(R.id.cipher_layout),
				mEncryptionKey);
		
		mInput.setText("UPILAIAPCHAKACUPDWEINACNEMNPREMETS");
		mEncryptionKey.setText("mayflower");		
	}
	
	@Override
	public void decrypt(View v) {
		String res = decrypt(mInput.getText().toString(), mEncryptionKey.getText().toString());
		mOutput.setText(res);
	}

}
