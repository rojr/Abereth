package com.gmail.robmadeyou.Draw;

import org.lwjgl.util.vector.Vector2f;

import com.gmail.robmadeyou.Layer;
import com.gmail.robmadeyou.Effects.Color;

public abstract class Drawable {
	
	private Vector2f coord;
	private Vector2f dimension;
	private int layer;
	private int texture;
	private Color color;
	private float opacity;
	public Drawable(Vector2f coord, Vector2f dimension){
		this.coord = coord;
		this.dimension = dimension;
		this.layer = Layer.GUILayer();
		this.opacity = 1f;
		this.color = Color.White;
		this.texture = -1;
	}
	
	/*
	 * Getters
	 */
	
	public float getDrawX(){
		return coord.x;
	}
	public float getDrawY(){
		return coord.y;
	}
	public float getDrawWidth(){
		return dimension.x;
	}
	public float getDrawHeight(){
		return dimension.y;
	}
	public int getLayer(){
		return layer;
	}
	public int getTexture(){
		return texture;
	}
	public Color getColor(){
		return color;
	}
	public float getOpacity(){
		return opacity;
	}
	
	/*
	 * Setters
	 */
	
	public void setDrawX(float x){
		this.coord.x = x;
	}
	public void setDrawY(float y){
		this.coord.y = y;
	}
	public void setDrawWidth(float width){
		this.dimension.x = width;
	}
	public void setDrawHeight(float height){
		this.dimension.y = height;
	}
	public void setLayer(int layer){
		this.layer = layer;
	}
	public void setTexture(int texture){
		this.texture = texture;
	}
	public void setColor(Color color){
		this.color = color;
	}
	public void setOpacity(float opacity){
		this.opacity = opacity;
	}
	
	/*
	 * Casuals
	 */
	
	protected void updateDrawValues(Vector2f coord, Vector2f dimension){
		this.coord = coord;
		this.dimension = dimension;
	}
	
	
	public abstract void draw();
}
