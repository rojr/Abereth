package com.age.graphics.render.shapes;

import com.age.graphics.Drawable;

public abstract class Shape extends Drawable{

	public Shape(double drawX, double drawY, double drawWidth, double drawHeight) {
		super(drawX, drawY, drawWidth, drawHeight);
	}

	@Override
	public abstract void draw();
}
