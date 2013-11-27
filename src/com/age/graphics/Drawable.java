package com.age.graphics;

import com.age.graphics.effects.Color;
import com.age.graphics.render.Collector;

public abstract class Drawable {

	private double drawX, drawY, drawWidth, drawHeight;
	private int layer, rotation, texture;
	private float scaleX, scaleY;
	private Color color;
	private boolean inverts, useTranslate;

	public Drawable(double drawX, double drawY, double drawWidth, double drawHeight) {
		this.drawX = drawX;
		this.drawY = drawY;
		this.drawWidth = drawWidth;
		this.drawHeight = drawHeight;
		this.layer = 1;
		this.rotation = 0;
		this.scaleX = 1;
		this.scaleY = 1;
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

	public float getScaleX() {
		return scaleX;
	}

	public float getScaleY() {
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

	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}

	public void setScaleY(float scaleY) {
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
	public void setScale(float x, float y){
		this.scaleX = x;
		this.scaleY = y;
	}
	
	public void setLocationByCenter(int x, int y){
		this.drawX = x - drawWidth / 2;
		this.drawY = y - drawHeight / 2;
	}
	
	public void render(){
		Collector.add(this);
	}
	public abstract void draw();

}
