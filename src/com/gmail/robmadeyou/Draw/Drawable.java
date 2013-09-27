package com.gmail.robmadeyou.Draw;

import org.lwjgl.util.vector.Vector2f;

public abstract class Drawable {
	
	private Vector2f coord;
	private Vector2f dimension;
	public Drawable(Vector2f coord, Vector2f dimension){
		this.coord = coord;
		this.dimension = dimension;
	}
	
	public float getX(){
		return coord.x;
	}
	public float getY(){
		return coord.y;
	}
	public float getWidth(){
		return dimension.x;
	}
	public float getHeight(){
		return dimension.y;
	}
	
	public void setX(float x){
		this.coord.x = x;
	}
	public void setY(float y){
		this.coord.y = y;
	}
	public void setWidth(float width){
		this.dimension.x = width;
	}
	public void setHeight(float height){
		this.dimension.y = height;
	}
	
	
	
	public abstract void draw();
}
