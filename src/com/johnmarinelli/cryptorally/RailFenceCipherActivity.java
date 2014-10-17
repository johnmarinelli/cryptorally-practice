package com.johnmarinelli.cryptorally;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

public class RailFenceCipherActivity extends CipherActivity {
	NumberPicker mEncryptionKey;
	
	private String decrypt(String input, int key){
		/*
		 * credit goes to:
		 *  http://times.imkrisna.com/2011/01/rail-fence-cipher-c-source-code-2/
		 */
		String decrypted = "";
		input = Utilities.sanitizeString(input);
		
		int inLength = input.length();
		int height = key;
		
		ArrayList<ArrayList<Integer>> railFence = new ArrayList<ArrayList<Integer>>(height);
		
		/*
		 * populate rows of railFence
		 */
		for(int i = 0; i < height; ++i) {
			railFence.add(new ArrayList<Integer>(inLength));
		}
		
		/*
		 * create a 2D array where each row corresponds to the 
		 * encrypted block's 2d representation, and each element
		 * of the row is the index of the decrypted texts' char in
		 * the encrypted text 
		 */
		int rowInc = 0, inc = 1;
		
		for(int i = 0; i < inLength; ++i) {
			if(rowInc + inc == height) {
				inc = -1;
			}
			else if(rowInc + inc == -1) {
				inc = 1;
			}
			
			railFence.get(rowInc).add(i);
			rowInc += inc;
		}
		
		int counter = 0;
		ArrayList<Character> buffer = new ArrayList<Character>(inLength);
		for(int i = 0; i < inLength; ++i) buffer.add('a');
		
		/*
		 * now we map the encrypted text's indices to the appropriate
		 * decrypted texts' indices
		 */
		for(int i = 0; i < height; ++i) {
			for(int j = 0; j < railFence.get(i).size(); ++j) {
				buffer.set(railFence.get(i).get(j), input.charAt(counter));
				counter++;
			}
		}
		
		decrypted = buffer.toString();
		return decrypted;
	}
	
	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* add number picker to layout */
		mEncryptionKey = new NumberPicker(this.getBaseContext());
		mEncryptionKey.setMinValue(2);
		mEncryptionKey.setMaxValue(100);
		
		Utilities.addViewsToLayout((LinearLayout) findViewById(R.id.cipher_layout),
				mEncryptionKey);
	}
	@SuppressLint("NewApi")
	@Override
	public void decrypt(View v) {
		mInput = (EditText) findViewById(R.id.cipher_input);
		int key = mEncryptionKey.getValue();
		mOutput.setText(decrypt(mInput.getText().toString(), key));
	}

}
