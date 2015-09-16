package com.technohome.AndriodTraining;

public class MyText {
	String text;
	int color;
	int gravity;
	float size;

	public MyText(String txt, int col, float sz, int grav) {
		text = txt;
		color = col;
		size = sz;
		gravity = grav;
	}

	public String toString() {
		return text;
	}
}
