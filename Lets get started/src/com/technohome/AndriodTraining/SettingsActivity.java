package com.technohome.AndriodTraining;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class SettingsActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);

		final SharedPreferences pr = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		OnSharedPreferenceChangeListener listener = new OnSharedPreferenceChangeListener() {
			@Override
			public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
				if (key.equals("bgColor")) {
					String color = pr.getString(key, null);
					MainActivity.tv.setBackgroundColor(Color.parseColor(color));
				}

			}
		};

		pr.registerOnSharedPreferenceChangeListener(listener);

	}
}
