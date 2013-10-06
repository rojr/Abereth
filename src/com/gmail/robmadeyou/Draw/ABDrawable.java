package com.gmail.robmadeyou.Draw;

import org.lwjgl.util.vector.Vector2f;

import com.gmail.robmadeyou.ABLayer;
import com.gmail.robmadeyou.Effects.ABColor;

public abstract class ABDrawable {
	
	private Vector2f coord;
	private Vector2f dimension;
	private int layer;
	private int texture;
	private ABColor color;
	private float opacity;
	private boolean useTranslate;
	public ABDrawable(Vector2f coord, Vector2f dimension){
		this.coord = coord;
		this.dimension = dimension;
		this.layer = ABLayer.GUILayer();
		this.opacity = 1f;
		this.color = ABColor.White;
		this.texture = -1;
		this.useTranslate = false;
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
	public ABColor getColor(){
		return color;
	}
	public float getOpacity(){
		return opacity;
	}
	public boolean getUseTranslate(){
		return useTranslate;
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
	public void setColor(ABColor color){
		this.color = color;
	}
	public void setOpacity(float opacity){
		this.opacity = opacity;
	}
	public void setUseTranslate(boolean args){
		this.useTranslate = args;
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
