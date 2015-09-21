package com.technohome.AndriodTraining;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LifeCycleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_life_cycle);
		
		Log.d("LifeCycle", "OnCreate(): this activity is being created and loaded into memory");
		
		Intent i = getIntent();
		String msg = i.getStringExtra("my message");
		
		TextView tv = (TextView) findViewById(R.id.textViewOtherActivity);
		tv.setText(msg);
	}
	
	public void clickReturn(View v){
		EditText et = (EditText) findViewById(R.id.editTextOther);
		String str = et.getText().toString();
		Intent i = new Intent();
		i.putExtra("my result", str);
		setResult(RESULT_OK,i);
		finish();
	}
	
	@Override
	protected void onStart() {
		Log.d("LifeCycle", "OnStart(): this activity is now shown on the screen, but in not active");
		super.onStart();
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		Log.d("LifeCycle", "OnRestoreInstanceState(): this activity is being restored");
		super.onRestoreInstanceState(savedInstanceState);
	}
	
	@Override
	protected void onResume() {
		Log.d("LifeCycle", "OnResume(): this activity is now active and running");
		super.onResume();
	}
	
	@Override
	public void onBackPressed() {
		Log.d("LifeCycle", "OnBackPressed(): the user pressed back button");
		super.onBackPressed();
	}
	
	@Override
	protected void onPause() {
		Log.d("LifeCycle", "OnPause(): this activity is being paused, no more interaction with user");
		super.onPause();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Log.d("LifeCycle", "OnSaveInstanceState(): this activity is being saved");
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onStop() {
		Log.d("LifeCycle", "OnStop(): this activity is stopped and hidden from the screen");
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		Log.d("LifeCycle", "OnDestroy(): this activity is being destroyed and removed from the memory");
		super.onDestroy();
	}
	
	@Override
	protected void onRestart() {
		Log.d("LifeCycle", "OnRestart(): this activity is being restarted");
		super.onRestart();
	}
	
}
