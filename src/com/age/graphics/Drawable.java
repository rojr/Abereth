package com.age.graphics;

import com.age.graphics.effects.Color;

public abstract class Drawable {

	private double drawX, drawY, drawWidth, drawHeight;
	private int layer, rotation, texture;
	private double scale, scaleX, scaleY;
	private Color color;
	private boolean inverts, useTranslate;

	public Drawable(double drawX, double drawY, double drawWidth,
			double drawHeight) {
		this.drawX = drawX;
		this.drawY = drawY;
		this.drawWidth = drawWidth;
		this.drawHeight = drawHeight;
		this.layer = 1;
		this.rotation = 0;
		this.scale = 1;
		this.scaleX = 0;
		this.scaleY = 0;
		this.texture = -1;
		this.inverts = false;
		this.color = Color.White;
		this.setUseTranslate(false);
	}

	public double getDrawX() {
		return drawX;
	}

	public double getDrawY() {
		return drawY;
	}

	public double getDrawWidth() {
		return drawWidth;
	}

	public double getDrawHeight() {
		return drawHeight;
	}

	public int getLayer() {
		return layer;
	}

	public int getRotation() {
		return rotation;
	}

	public double getScale() {
		return scale;
	}

	public double getScaleX() {
		return scaleX;
	}

	public double getScaleY() {
		return scaleY;
	}

	public int getTexture() {
		return texture;
	}

	public Color getColor() {
		return color;
	}

	public boolean isUseTranslate() {
		return useTranslate;
	}

	public void setDrawX(double x) {
		this.drawX = x;
	}

	public void setDrawY(double y) {
		this.drawY = y;
	}

	public void setDrawWidth(double width) {
		this.drawHeight = width;
	}

	public void setDrawHeight(double height) {
		this.drawHeight = height;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
	}

	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}

	public boolean isInverts() {
		return inverts;
	}

	public void setTexture(int texture) {
		this.texture = texture;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setInverts(boolean inverts) {
		this.inverts = inverts;
	}

	public void setUseTranslate(boolean useTranlate) {
		this.useTranslate = useTranlate;
	}

	public abstract void draw();

}
