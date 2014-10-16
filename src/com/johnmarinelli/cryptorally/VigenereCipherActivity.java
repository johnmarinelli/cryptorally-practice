package com.johnmarinelli.cryptorally;

import java.util.ArrayList;

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
		input = input.toUpperCase();
		input = input.replaceAll("\\s+","");
		
		key = key.toUpperCase();
		key = key.replaceAll("\\s+", "");
		
		int inLength = input.length(), keyLength = key.length();
		
		/*
		 * Map characters to numbers 0-25
		 */
		ArrayList<Character> enumeratedInput = new ArrayList<Character>(inLength);
		ArrayList<Character> enumeratedKey = new ArrayList<Character>(keyLength);
		
		for(int i = 0; i < inLength; ++i) {
			enumeratedInput.add((char)(input.charAt(i) - Utilities.ASCII_CHAR_MIN));
			enumeratedKey.add((char)(key.charAt(i%keyLength) - Utilities.ASCII_CHAR_MIN));

			Log.i("inputkey", Integer.toString(enumeratedInput.get(i)));
			Log.i("enumkey", Integer.toString(enumeratedKey.get(i%keyLength)));
		}
		
		for(int j = 0; j < inLength; ++j){
			int enChar = (int)(enumeratedInput.get(j));
			int deChar = (int)(enumeratedKey.get(j%keyLength));
			
			Log.i("enchar", Integer.toString(enChar));
			Log.i("dechar", Integer.toString(deChar));
			
			int diff = enChar - deChar < 0 ?
					Utilities.ALPHABET_LENGTH - Math.abs(enChar - deChar) : 
					enChar - deChar;
			Log.i("diff", Integer.toString(diff));
			/*int diff = (enChar + deChar) % Utilities.ALPHABET_LENGTH;
			
			Log.i("diff", Integer.toString(diff));*/
			
			diff += Utilities.ASCII_CHAR_MIN;
			
			decrypted += (char) diff;
		}
		
		return decrypted;
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mInput.setText("XEVRBETYPSLNECM");
		
		/* add edit text to layout */
		mEncryptionKey = new EditText(this.getBaseContext());
		mEncryptionKey.setText("rain");
		
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
