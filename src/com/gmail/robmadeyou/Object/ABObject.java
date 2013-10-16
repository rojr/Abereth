package com.gmail.robmadeyou.object;

import org.lwjgl.util.vector.Vector2f;

import com.gmail.robmadeyou.draw.ABDrawable;
import com.gmail.robmadeyou.entity.ABEntity;
import com.gmail.robmadeyou.peripherals.ABMouse;
import com.gmail.robmadeyou.world.ABWorld;

public abstract class ABObject extends ABDrawable{

	
	private Vector2f coord = new Vector2f(), dimension = new Vector2f(); 
	private String state;
	public ABObject(float x, float y) {
		super(new Vector2f(), new Vector2f());
		this.coord.x = x;
		this.coord.y = y;
		this.dimension.x = ABWorld.BLOCK_SIZE();
		this.dimension.y = ABWorld.BLOCK_SIZE();
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
	
	public ABObject setX(float x){
		setDrawX(x);
		return this;
	}
	public ABObject setY(float y){
		setDrawY(y);
		return this;
	}
	public ABObject setWidth(float width){
		setDrawWidth(width);
		return this;
	}
	public ABObject setHeight(float height){
		setDrawHeight(height);
		return this;
	}
	
	public ABObject setState(String state){
		this.state = state;
		return this;
	}
	
	/*
	 * Casuals 
	 */
	
	public boolean isNear(ABEntity other) {
        float oX = other.getX() + getWidth() / 2;
        float oY = other.getY() + getHeight() / 2;
        return oX >= getX() && oX <= getX() + getWidth() && oY >= getY() && oY <= getY() + getHeight();
    }

    public boolean isMouseOver() {
        int mX = (int)ABMouse.getTranslatedX();
        int mY = (int)ABMouse.getTranslatedY();
        return mX >= getX() && mX <= getX() + getWidth() && mY >= getY() && mY <= getY() + getHeight();
    }
	
	
	public abstract void onUpdate();
	
	public void draw() {
		
	}

}
