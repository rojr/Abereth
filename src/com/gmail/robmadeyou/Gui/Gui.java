package com.gmail.robmadeyou.Gui;

import org.lwjgl.util.vector.Vector2f;

import com.gmail.robmadeyou.Draw.Drawable;
import com.gmail.robmadeyou.Input.Mouse;

public abstract class Gui extends Drawable{


	private String state;
	public Gui(float x, float y) {
		super(new Vector2f(), new Vector2f());
		setX(x);
		setY(y);
		setWidth(50);
		setHeight(50);
		state = "";
	}
	
	/*
	 * Getters
	 */
	
	
	public float getX(){
		return getDrawX();
	}
	public float getY(){
		return getDrawY();
	}
	public float getWidth(){
		return getDrawWidth();
	}
	public float getHeight(){
		return getDrawHeight();
	}
	public String getState(){
		return state;
	}
	
	
	/*
	 * Setters
	 */
	
	
	public Gui setX(float x){
		setDrawX(x);
		return this;
	}
	public Gui setY(float y){
		setDrawY(y);
		return this;
	}
	public Gui setWidth(float width){
		setDrawWidth(width);
		return this;
	}
	public Gui setHeight(float height){
		setDrawHeight(height);
		return this;
	}
	public Gui setState(String state){
		this.state = state;
		return this;
	}
	
	/*
	 * Casuals
	 */
	
	public boolean isMouseOver(){
        int mX = (int) Mouse.getTranslatedX();
        int mY = (int) Mouse.getTranslatedY();
        if (mX >= getX() && mX <= getY() + getWidth() && mY >= getY() && mY <= getY() + getHeight()) {
            return true;
        }
        return false;
	}
	
	public abstract void onUpdate();
	
	public void update(){
		onUpdate();
		draw();
	}
	
	public abstract void draw();
}
