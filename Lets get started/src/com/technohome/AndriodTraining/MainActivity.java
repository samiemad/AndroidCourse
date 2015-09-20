package com.technohome.AndriodTraining;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

	TextView tv;
	private EditText et;
	private SeekBar seekBar1;
	private ArrayAdapter<MyText> adapter;
	private ArrayList<MyText> arrayOfHistory = new ArrayList<MyText>();

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
			Toast.makeText(this, "Settings action butoon is pressed", Toast.LENGTH_SHORT).show();
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
		} else if (id == R.id.action_chooseBG) {
			showBGChooseDialog();
		}

		return true;
	}

	int r, g, b;

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
		// AlertDialog dlg = new AlertDialog.Builder(this).create();
		// dlg.setTitle("Exit");
		// dlg.setMessage("Are you sure you want to exit?");
		// dlg.setIcon(android.R.drawable.ic_dialog_info);
		// dlg.setCancelable(false);
		// dlg.setCanceledOnTouchOutside(false);
		// DialogInterface.OnClickListener lstnr = new
		// DialogInterface.OnClickListener() {
		//
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// if (which == AlertDialog.BUTTON_POSITIVE) {
		// MainActivity.super.onBackPressed();
		// }
		// dialog.dismiss();
		// }
		// };
		// dlg.setButton(AlertDialog.BUTTON_NEGATIVE, "No", lstnr);
		// dlg.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", lstnr);
		// dlg.show();
		// super.onBackPressed();
	}
}
