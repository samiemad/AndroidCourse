package com.technohome.AndriodTraining;

import java.util.Random;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class AutoBoomActivity extends Activity {

	TextView tv;
	SeekBar sb;
	ToggleButton tbMusic;
	Switch sw;

	MediaPlayer mp;

	long timeDelay = 500;

	Runnable r = new Runnable() {
		Random rnd = new Random();

		@Override
		public void run() {
			int color = rnd.nextInt();
			tv.setTextColor(color);
			int gravity = rnd.nextInt();
			tv.setGravity(gravity);
			float size = rnd.nextFloat() * 100;
			tv.setTextSize(size);

			tv.postDelayed(r, timeDelay);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auto_boom);
		linkXML();

		setSeekBar();

		tv.post(r);

		setupSwitch();

		setupMediaPlayer();
	}

	private void setupMediaPlayer() {
		mp = MediaPlayer.create(this, R.raw.alimba);
		mp.start();
	}

	private void setupSwitch() {
		OnCheckedChangeListener listener = new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					tv.post(r);
				} else {
					tv.removeCallbacks(r);
				}
			}
		};
		sw.setOnCheckedChangeListener(listener);
	}

	private void setSeekBar() {
		OnSeekBarChangeListener l = new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				timeDelay = progress;
			}
		};
		sb.setOnSeekBarChangeListener(l);
	}

	@Override
	protected void onDestroy() {
		if (mp != null)
			mp.release();
		super.onDestroy();
	}

	private void linkXML() {
		tv = (TextView) findViewById(R.id.textViewAuto);
		sw = (Switch) findViewById(R.id.switchAuto);
		tbMusic = (ToggleButton) findViewById(R.id.toggleButtonMusic);
		sb = (SeekBar) findViewById(R.id.seekBarTimeDelay);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.auto_boom, menu);
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
}
