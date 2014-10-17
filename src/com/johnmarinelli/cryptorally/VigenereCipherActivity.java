package com.johnmarinelli.cryptorally;

import java.util.ArrayList;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class VigenereCipherActivity extends CipherActivity {
	EditText mEncryptionKey;
	
	private String decrypt(String input, String key) {
		String decrypted = "";
		input = Utilities.sanitizeString(input);
		key = Utilities.sanitizeString(key);
		/*input = input.toUpperCase();
		input = input.replaceAll("\\s+","");
		
		key = key.toUpperCase();
		key = key.replaceAll("\\s+", "");*/
		
		int inLength = input.length(), keyLength = key.length();
		
		/*
		 * Map characters to numbers 0-25
		 */
		ArrayList<Character> enumeratedInput = new ArrayList<Character>(inLength);
		ArrayList<Character> enumeratedKey = new ArrayList<Character>(keyLength);
		
		for(int i = 0; i < inLength; ++i) {
			enumeratedInput.add((char)(input.charAt(i) - Utilities.ASCII_CHAR_MIN));
			enumeratedKey.add((char)(key.charAt(i%keyLength) - Utilities.ASCII_CHAR_MIN));
		}
		
		for(int j = 0; j < inLength; ++j){
			int enChar = (int)(enumeratedInput.get(j));
			int deChar = (int)(enumeratedKey.get(j%keyLength));
			
			int diff = enChar - deChar < 0 ?
					Utilities.ALPHABET_LENGTH - Math.abs(enChar - deChar) : 
					enChar - deChar;
			
			diff += Utilities.ASCII_CHAR_MIN;
			
			decrypted += (char) diff;
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
	}
	
	@Override
	public void decrypt(View v) {
		mInput = (EditText) findViewById(R.id.cipher_input);
		String res = decrypt(mInput.getText().toString(), 
				mEncryptionKey.getText().toString());
		
		mOutput = (TextView) findViewById(R.id.cipher_output);
		mOutput.setText(res);
	}
}
