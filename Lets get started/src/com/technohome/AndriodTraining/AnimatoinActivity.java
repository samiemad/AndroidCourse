package com.technohome.AndriodTraining;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AnimatoinActivity extends Activity {

	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animatoin);

		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

		ListView lv = (ListView) findViewById(R.id.listView1);
		lv.setAdapter(adapter);

	}

	public void clickGo(View v) {
		Intent i = new Intent(this, Animation2Activity.class);
		ActivityOptions options = ActivityOptions.makeScaleUpAnimation(v, 0, 0, v.getWidth(), v.getHeight());
		startActivity(i, options.toBundle());
	}

}
