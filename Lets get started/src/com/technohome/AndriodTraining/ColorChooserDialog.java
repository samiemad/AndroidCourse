package com.technohome.AndriodTraining;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ColorChooserDialog extends Dialog {
	int r, g, b;

	public ColorChooserDialog(final MainActivity c) {
		super(c);
		this.setContentView(R.layout.dialog_chooser);
		setTitle("Choose a color");
		Button ok = (Button) this.findViewById(R.id.buttonOk);
		Button cancel = (Button) this.findViewById(R.id.buttonCancel);
		final TextView preview = (TextView) this.findViewById(R.id.textViewPreview);
		SeekBar red = (SeekBar) this.findViewById(R.id.seekBarRed);
		SeekBar green = (SeekBar) this.findViewById(R.id.seekBarGreen);
		SeekBar blue = (SeekBar) this.findViewById(R.id.seekBarBlue);
		OnSeekBarChangeListener l = new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				if (seekBar.getId() == R.id.seekBarRed) {
					r = progress;
				} else if (seekBar.getId() == R.id.seekBarGreen) {
					g = progress;
				} else if (seekBar.getId() == R.id.seekBarBlue) {
					b = progress;
				}
				preview.setBackgroundColor(Color.rgb(r, g, b));
			}
		};
		red.setOnSeekBarChangeListener(l);
		green.setOnSeekBarChangeListener(l);
		blue.setOnSeekBarChangeListener(l);
		View.OnClickListener btnlstnr = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (v.getId() == R.id.buttonOk) {
					c.tv.setBackgroundColor(Color.rgb(r, g, b));
				}
				ColorChooserDialog.this.dismiss();
			}
		};
		ok.setOnClickListener(btnlstnr);
		cancel.setOnClickListener(btnlstnr);
	}

}
