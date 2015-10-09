package com.technohome.AndriodTraining;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	Random rnd = new Random();

	static TextView tv;
	private EditText et;
	private SeekBar seekBar1;
	private ArrayAdapter<MyText> adapter;
	private ArrayList<MyText> arrayOfHistory = new ArrayList<MyText>();

	private SharedPreferences pr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pr = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

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

		adapter = new ArrayAdapter<MyText>(this, android.R.layout.simple_list_item_1, arrayOfHistory);
		final ListView myListView = (ListView) findViewById(R.id.listViewHistory);
		myListView.setAdapter(adapter);
		myListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				tv.setText(arrayOfHistory.get(position).text);
				tv.setTextSize(arrayOfHistory.get(position).size);
				tv.setTextColor(arrayOfHistory.get(position).color);
				tv.setGravity(arrayOfHistory.get(position).gravity);
				tv.setVisibility(View.VISIBLE);
				myListView.setVisibility(View.INVISIBLE);
			}
		});
		myListView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				// adapter.remove(arrayOfHistory.get(position));
				showDeleteDialog(position);
				return true;
			}
		});

		tv.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				tv.setVisibility(View.INVISIBLE);
				myListView.setVisibility(View.VISIBLE);
				return true;
			}
		});

		// Day8: extract the settings and apply them to the app:
		// - say hello to the user with his/her name:
		String name = pr.getString("username", null);
		if (name != null) {
			Toast.makeText(this, "Welcome " + name, Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(this, "Hello World", Toast.LENGTH_LONG).show();
		}
		// - Restore the background color chosen by the user:
		String color = pr.getString("bgColor", null);
		boolean customColor = pr.getBoolean("customColor", false);
		if (color != null && customColor == true) {
			tv.setBackgroundColor(Color.parseColor(color));
		}
	}

	private void showDeleteDialog(final int position) {
		AlertDialog dlg = new AlertDialog.Builder(this).create();
		dlg.setTitle("Confirm");
		dlg.setMessage("are you sure you want to delete " + arrayOfHistory.get(position) + "?");
		dlg.setIcon(android.R.drawable.ic_dialog_alert);
		dlg.setCancelable(true);
		dlg.setCanceledOnTouchOutside(true);
		DialogInterface.OnClickListener lstnr = new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (which == AlertDialog.BUTTON_POSITIVE) {
					adapter.remove(arrayOfHistory.get(position));
				}
				dialog.dismiss();
			}
		};
		dlg.setButton(AlertDialog.BUTTON_NEGATIVE, "No", lstnr);
		dlg.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", lstnr);
		dlg.show();
	}

	public void clickBoom(View v) {
		Toast.makeText(this, "Hello World!", Toast.LENGTH_LONG).show();

		int color = Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
		tv.setTextColor(color);

		float size = rnd.nextFloat() * 100;
		tv.setTextSize(size);

		seekBar1.setProgress((int) (tv.getTextSize() / 10));
		int gravity = rnd.nextInt();
		tv.setGravity(gravity);

		String txt = et.getText().toString();
		tv.setText(txt);

		adapter.add(new MyText(txt, color, size, gravity));

	}

	private MenuItem menuClose;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		menuClose = menu.findItem(R.id.action_close);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_close) {
			finish();
		} else if (id == R.id.action_settings) {
			// Toast.makeText(this, "Settings action butoon is pressed",
			// Toast.LENGTH_SHORT).show();
			Intent i = new Intent(this, SettingsActivity.class);
		} else if (id == R.id.action_red) {
			tv.setBackgroundColor(Color.RED);
			item.setChecked(true);
		} else if (id == R.id.action_green) {
			tv.setBackgroundColor(Color.GREEN);
			item.setChecked(true);
		} else if (id == R.id.action_blue) {
			tv.setBackgroundColor(Color.BLUE);
			item.setChecked(true);
		} else if (id == R.id.action_showExit) {
			if (item.isChecked()) {
				menuClose.setVisible(true);
				item.setChecked(false);
				item.setTitle("hide exit");
			} else {
				menuClose.setVisible(false);
				item.setChecked(true);
				item.setTitle("show exit");
			}
		} else if (id == R.id.action_chooseColor) {
			showBGChooseDialog();
		} else if (id == R.id.action_startLifeCycle) {
			Intent i = new Intent(MainActivity.this, LifeCycleActivity.class);
			i.putExtra("my message", "this is data from MAinActivity");
			// startActivity(i);

			startActivityForResult(i, 15);
			// startActivity(new Intent(this, LifeCycleActivity.class));
		}

		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		String color = pr.getString("bgColor", null);
		boolean b = pr.getBoolean("customColor", false);
		if (b == true && color != null) {
			tv.setBackgroundColor(Color.parseColor(color));
		} else {
			tv.setBackgroundColor(0);
		}
	}

	private void showBGChooseDialog() {
		final Dialog dlg = new ColorChooserDialog(this);
		dlg.show();
	}

	boolean exit = false;

	@Override
	public void onBackPressed() {
		if (exit == false) {
			exit = true;
			Toast.makeText(this, "Press back again to exit", Toast.LENGTH_LONG).show();
			Thread th = new Thread() {
				@Override
				public void run() {
					try {
						sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						exit = false;
					}
				}
			};
			th.start();
		} else {
			super.onBackPressed();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 15) {
			if (resultCode == RESULT_OK) {
				String res = data.getStringExtra("my result");
				int x = data.getIntExtra("result Int", 0);
				Toast.makeText(this, res + "x = " + x, Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "result not ok!", Toast.LENGTH_LONG).show();
			}
		}
	}
}
