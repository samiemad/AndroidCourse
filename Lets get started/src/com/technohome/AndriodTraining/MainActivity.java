package com.technohome.AndriodTraining;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ToggleButton tb =(ToggleButton) findViewById(R.id.toggleButton1);
		OnCheckedChangeListener ll = new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean pressed) {
				EditText et = (EditText) findViewById(R.id.editText1);
				if(pressed){
					et.setVisibility(View.GONE);
				}else
					et.setVisibility(View.VISIBLE);
			}
		};
		tb.setOnCheckedChangeListener(ll );
		
		
		SeekBar seek = (SeekBar) findViewById(R.id.seekBar1);
		OnSeekBarChangeListener l = new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
			}
			
			@Override
			public void onProgressChanged(SeekBar arg0, int val, boolean b) {
				TextView tv = (TextView) findViewById(R.id.textView1);
				tv.setTextSize(val);
			}
		};
		seek.setOnSeekBarChangeListener(l );
	}

	public void clickBoom(View v) {
		Toast.makeText(this, "Hello World!", Toast.LENGTH_LONG).show();
		
		Random rnd = new Random();
		TextView tv = (TextView) findViewById(R.id.textView1);
		EditText et = (EditText) findViewById(R.id.editText1);
		tv.setTextColor(Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
		tv.setTextSize(rnd.nextFloat()*100);
		tv.setGravity(rnd.nextInt());
		
		String txt = et.getText().toString();
		tv.setText(txt);
		
		
	}

}
