package com.technohome.AndriodTraining;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Animation2Activity extends Activity {

	boolean loading = true;
	ProgressBar loadingCircle;
	TextView data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation2);

		loadingCircle = (ProgressBar) findViewById(R.id.progressBar1);
		data = (TextView) findViewById(R.id.textViewData);

	}

	private void showLoading() {
		loadingCircle.setTranslationX(-data.getWidth());
		loadingCircle.setTranslationY(-data.getHeight());
		loadingCircle.setScaleX(3);
		loadingCircle.setScaleY(3);
		loadingCircle.setAlpha(1);
		loadingCircle.animate().translationX(0).translationY(0).scaleX(1).scaleY(1).start();
		data.animate().translationY(data.getHeight()).scaleX(0).scaleY(0).start();

	}

	private void hideLoading() {
		loadingCircle.animate().scaleX(2).scaleY(0).start();
		data.setRotation(180);

		data.animate().alpha(1f).rotation(0).scaleX(1).scaleY(1).translationY(0).start();
	}
}
