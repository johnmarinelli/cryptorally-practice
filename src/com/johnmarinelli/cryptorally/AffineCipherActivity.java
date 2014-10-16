package com.johnmarinelli.cryptorally;

import com.johnmarinelli.cryptorally.Utilities;
import com.johnmarinelli.cryptorally.OrderedPair;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.os.Bundle;

public class AffineCipherActivity extends CipherActivity {
	NumberPicker mEncryptLeftVal;
	NumberPicker mEncryptRightVal;
	
	TextView mDecryptKeyText;
	
	OrderedPair<Integer, Integer> mDecryptKey;
	
	private String decrypt(String in, OrderedPair<Integer, Integer> ekey) {
		String decrypted = "";
		in = in.toUpperCase();
		
		int dX = Utilities.getModularInv(ekey.getX(), Utilities.ALPHABET_LENGTH);
		int dY = (dX * (Utilities.ALPHABET_LENGTH - ekey.getY())) % Utilities.ALPHABET_LENGTH;
				
		mDecryptKey.setX(dX);
		mDecryptKey.setY(dY);
		
		for(int c : in.toCharArray()) {
			/* 65-90 to 0-25 */
			c -= Utilities.ASCII_CHAR_MIN;

			c *= dX;
			c += dY;
			
			c %= Utilities.ALPHABET_LENGTH;

			Log.i("c", Integer.toString(c));
			/* 0-25 to 65-90 */
			c += Utilities.ASCII_CHAR_MIN;
			
			decrypted += (char) c;
		}
		
		return decrypted;		
	}

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mEncryptLeftVal = new NumberPicker(this.getBaseContext());
		mEncryptRightVal = new NumberPicker(this.getBaseContext());
		
		mEncryptLeftVal.setMinValue(0);
		mEncryptRightVal.setMinValue(0);
		mEncryptLeftVal.setMaxValue(25);
		mEncryptRightVal.setMaxValue(25);
		
		mDecryptKeyText = new TextView(this.getBaseContext());
		mDecryptKey = new OrderedPair<Integer, Integer>(0, 0);
		
		Utilities.addViewsToLayout((LinearLayout) findViewById(R.id.cipher_layout),
				mEncryptLeftVal, mEncryptRightVal, mDecryptKeyText);		
	}

	@SuppressLint("NewApi")
	@Override
	public void decrypt(View v) {
		String in = mInput.getText().toString();
		
		int x = mEncryptLeftVal.getValue();
		int y = mEncryptRightVal.getValue();
		
		OrderedPair<Integer, Integer> ekey = 
				new OrderedPair<Integer, Integer>(x, y);
		
		mOutput = (TextView) findViewById(R.id.cipher_output);
		
		mOutput.setText(decrypt(in, ekey));
		mDecryptKeyText.setText("Decrypt key: " + mDecryptKey.getX()
				+ ", " + mDecryptKey.getY());
	}
}
