package com.age.graphics;

public class Drawable {
	
	
	private double x, y, width,height;
	private int layer, rotation;
	private double scale, scaleX, scaleY;
	public Drawable(double x, double y, double width, double height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.layer = 1;
		this.rotation = 0;
		this.scale = 0;
		this.scaleX = 0;
		this.scaleY = 0;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getWidth() {
		return width;
	}
	public double getHeight() {
		return height;
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
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public void setHeight(double height) {
		this.height = height;
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
}
