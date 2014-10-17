package com.johnmarinelli.cryptorally;

import com.johnmarinelli.cryptorally.Utilities;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

public class ShiftCipherActivity extends CipherActivity {
	NumberPicker mEncryptionKey;
	
	private String decrypt(String in, int key) {
		String decrypted = "";
		in = Utilities.sanitizeString(in);
		
		for(char c : in.toCharArray()) {
			/* ((go from 65-90 to 0-25) + key) % 26*/
			int shiftAmt = ((c - Utilities.ASCII_CHAR_MIN) + key) % 26; 
			/* add shift amount to 65 */
			decrypted += (char)(Utilities.ASCII_CHAR_MIN + shiftAmt);
		}
		
		return decrypted;
	}

	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* add number picker to layout */
		mEncryptionKey = new NumberPicker(this.getBaseContext());
		mEncryptionKey.setMinValue(0);
		mEncryptionKey.setMaxValue(25);
		
		Utilities.addViewsToLayout((LinearLayout) findViewById(R.id.cipher_layout),
				mEncryptionKey);		
	}
	
	@SuppressLint("NewApi")
	@Override
	public void decrypt(View v) {
		String res = decrypt(mInput.getText().toString(), mEncryptionKey.getValue());
		mOutput.setText(res);
	}

}
