package com.johnmarinelli.cryptorally;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

abstract class CipherActivity extends ActionBarActivity {

	EditText mInput;
	TextView mOutput;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cipher);
		
		mInput = (EditText) findViewById(R.id.cipher_input);
	}
	
	abstract void decrypt(View v);
}
