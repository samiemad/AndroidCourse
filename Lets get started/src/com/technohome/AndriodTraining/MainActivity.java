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
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	Random rnd = new Random();

	TextView tv;
	EditText et;
	SeekBar seekBar1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView) findViewById(R.id.textView1);
		et = (EditText) findViewById(R.id.editText1);

		// link the seekbar1 with the XMLobject
		seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
		OnSeekBarChangeListener l = new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
			}

			@Override
			public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
				//
				tv.setTextSize((progress + 1) * 10);
			}
		};
		// attach the listener with seekbar1
		seekBar1.setOnSeekBarChangeListener(l);

		// find the Toggle Button View from the XML layout file:
		ToggleButton tg = (ToggleButton) findViewById(R.id.toggleButton1);

		// Create the listener that will handle the toggleButton clicks:
		OnCheckedChangeListener listener = new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean pressed) {
				// Show/Hide the EditText when the toggle Button is pressed:
				if (pressed) {
					et.setVisibility(View.INVISIBLE);
				} else {
					et.setVisibility(View.VISIBLE);
				}
			}
		};
		// attach the listener to the Toggle Button:
		tg.setOnCheckedChangeListener(listener);

		// Find the Number Picker view from the XML layout file:
		NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker1);

		// Create a listener to hanlde the number picker change events:
		OnValueChangeListener numchanged = new OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker arg0, int arg1, int arg2) {
				// change the textView background color as an example.
				int c = Color.rgb(arg1, arg2, 100);
				tv.setBackgroundColor(c);
			}
		};
		// attach the listener to the Number Picker:
		np.setOnValueChangedListener(numchanged);

		// Don't forget to set the min/max/current Values for the Number Picker:
		np.setMaxValue(255);
		np.setMinValue(0);
		np.setValue(200);

	}

	public void clickBoom(View v) {
		Toast.makeText(this, "Hello World!", Toast.LENGTH_LONG).show();

		tv.setTextColor(Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
		tv.setTextSize(rnd.nextFloat() * 100);
		seekBar1.setProgress((int) (tv.getTextSize() / 10));
		tv.setGravity(rnd.nextInt());

		String txt = et.getText().toString();
		tv.setText(txt);

	}

}
