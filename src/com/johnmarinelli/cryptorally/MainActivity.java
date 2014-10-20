package com.johnmarinelli.cryptorally;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utilities.init();
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void startShiftCipher(View v) {
    	Intent intent = new Intent(this, ShiftCipherActivity.class);
    	startActivity(intent);
    }
    
    public void startAffineCipher(View v) {
    	Intent intent = new Intent(this, AffineCipherActivity.class);
    	startActivity(intent);
    }
    
    public void startVigenereCipher(View v) {
    	Intent intent = new Intent(this, VigenereCipherActivity.class);
    	startActivity(intent);
    }
    
    public void startRailFenceCipher(View v) {
    	Intent intent = new Intent(this, RailFenceCipherActivity.class);
    	startActivity(intent);
    }
    
    public void startCTCipher(View v) {
    	Intent intent = new Intent(this, CTCipherActivity.class);
    	startActivity(intent);
    }
}
