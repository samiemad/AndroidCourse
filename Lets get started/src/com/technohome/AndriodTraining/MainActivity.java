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

// this is the Main Screen class, we inherited from class Activity
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// display the XML layout we created in activity_main.xml on the screen
		setContentView(R.layout.activity_main);

		// declare the toggle button and link it with the onCheckedChanged
		// listener;
		ToggleButton tb = (ToggleButton) findViewById(R.id.toggleButton1);
		OnCheckedChangeListener ll = new OnCheckedChangeListener() {

			// this method will be called every time the toggle button changes
			// state from on->off or off->on the method will be called.
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean pressed) {
				EditText et = (EditText) findViewById(R.id.editText1);
				// Show or hide the input text view as an example of doing
				// something.
				if (pressed) {
					et.setVisibility(View.GONE);
				} else
					et.setVisibility(View.VISIBLE);
			}
		};
		// don't forget to set the listener to link it with the ToggleButton
		tb.setOnCheckedChangeListener(ll);

		// an Example of using SeekBar and changing the textSize when the user
		// slides the slider
		SeekBar seek = (SeekBar) findViewById(R.id.seekBar1);

		// Create a listener and implement the work that should be dont when the
		// slider value changes.
		OnSeekBarChangeListener l = new OnSeekBarChangeListener() {

			// we don't need to add anything here.
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
			}

			@Override
			public void onProgressChanged(SeekBar arg0, int val, boolean b) {
				// this will change the TextSize on the screen.
				TextView tv = (TextView) findViewById(R.id.textView1);
				tv.setTextSize(val);
			}
		};

		// don't forget to actually set the listener to link it with the SeekBar
		seek.setOnSeekBarChangeListener(l);
	}

	// this is the method that will be called when the user presses the "Boom"
	// button.
	// note: that the method must be public, void, and must take one param that
	// is a View.
	public void clickBoom(View v) {
		// this displays a little message on the screen.
		Toast.makeText(this, "Hello World!", Toast.LENGTH_LONG).show();

		// create a random generator to use later:
		Random rnd = new Random();
		TextView tv = (TextView) findViewById(R.id.textView1);
		EditText et = (EditText) findViewById(R.id.editText1);

		// set a random text color... using RGB scale
		tv.setTextColor(Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));

		// set a random text size
		// rnd.nextFloat() generates a random real number between 0 and 1
		tv.setTextSize(rnd.nextFloat() * 100);

		// change the text alignment on the screen:
		// (top, bottom, left, right, center etc... )
		tv.setGravity(rnd.nextInt());

		// get the text from the input EditText
		String txt = et.getText().toString();
		// set it to the displayed text
		tv.setText(txt);

	}

}
