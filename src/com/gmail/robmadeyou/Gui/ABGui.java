package com.gmail.robmadeyou.gui;

import org.lwjgl.util.vector.Vector2f;

import com.gmail.robmadeyou.draw.ABDrawable;
import com.gmail.robmadeyou.peripherals.ABMouse;

public abstract class ABGui extends ABDrawable{


	private String state;
	public ABGui(float x, float y) {
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
	
	
	public ABGui setX(float x){
		setDrawX(x);
		return this;
	}
	public ABGui setY(float y){
		setDrawY(y);
		return this;
	}
	public ABGui setWidth(float width){
		setDrawWidth(width);
		return this;
	}
	public ABGui setHeight(float height){
		setDrawHeight(height);
		return this;
	}
	public ABGui setState(String state){
		this.state = state;
		return this;
	}
	public ABGui setLayer(int layer){
		super.setDrawLayer(layer);
		return this;
	}
	
	/*
	 * Casuals
	 */
	
	public boolean isMouseOver(){
        float mX = 0;
        float mY = 0;
        if(getUseTranslate()){
        	mX = ABMouse.getTranslatedX();
            mY = ABMouse.getTranslatedY();
        }else{
        	mX = ABMouse.getX();
            mY = ABMouse.getY();
        }
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
