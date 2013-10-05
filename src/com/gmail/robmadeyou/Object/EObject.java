package com.gmail.robmadeyou.Object;

import org.lwjgl.util.vector.Vector2f;

import com.gmail.robmadeyou.Draw.Drawable;
import com.gmail.robmadeyou.Entity.Entity;
import com.gmail.robmadeyou.Input.Mouse;
import com.gmail.robmadeyou.World.World;

public abstract class EObject extends Drawable{

	
	private Vector2f coord = new Vector2f(), dimension = new Vector2f(); 
	private String state;
	public EObject(float x, float y) {
		super(new Vector2f(), new Vector2f());
		this.coord.x = x;
		this.coord.y = y;
		this.dimension.x = World.BLOCK_SIZE();
		this.dimension.y = World.BLOCK_SIZE();
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
	
	public EObject setX(float x){
		setDrawX(x);
		return this;
	}
	public EObject setY(float y){
		setDrawY(y);
		return this;
	}
	public EObject setWidth(float width){
		setDrawWidth(width);
		return this;
	}
	public EObject setHeight(float height){
		setDrawHeight(height);
		return this;
	}
	
	public EObject setState(String state){
		this.state = state;
		return this;
	}
	
	/*
	 * Casuals 
	 */
	
	public boolean isNear(Entity other) {
        float oX = other.getX() + getWidth() / 2;
        float oY = other.getY() + getHeight() / 2;
        return oX >= getX() && oX <= getX() + getWidth() && oY >= getY() && oY <= getY() + getHeight();
    }

    public boolean isMouseOver() {
        int mX = (int)Mouse.getTranslatedX();
        int mY = (int)Mouse.getTranslatedY();
        return mX >= getX() && mX <= getX() + getWidth() && mY >= getY() && mY <= getY() + getHeight();
    }
	
	
	public abstract void onUpdate();
	
	public void draw() {
		
	}

}
