package com.age.graphics;

import com.age.Age;
import com.age.Screen;
import com.age.graphics.render.Render;

public class Camera {

	private int screenX, screenY, width, height;
	private float x, y;
	private FollowStyle followStyle;
	private boolean followingTarget;
	private Drawable target;
	private float speed;
	public Camera(int screenX, int screenY, int width, int height){
		this.screenX = screenX;
		this.screenY = screenY;
		this.width = width;
		this.height = height;
		this.followStyle = FollowStyle.SOFT;
		this.speed = 5f;
		this.followingTarget = true;
		this.x = 0;
		this.y = 0;
	}
	
	public Camera setFollowStyle(FollowStyle f){
		this.followStyle = f;
		return this;
	}
	
	public int getX(){
		return screenX;
	}
	public int getY(){
		return screenY;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	
	public float getTranslateX(){
		return x;
	}
	public float getTranslateY(){
		return y;
	}
	public Drawable getTarget(){
		return target;
	}
	
	public void setTranslateX(float x){
		this.x = x;
	}
	public void setTranslateY(float y){
		this.y = y;
	}
	
	public void setTarget(Drawable t){
		this.target = t;
	}
	public void setWidth(int width){
		this.width = width;
	}
	public void setHeight(int height){
		this.height = height;
	}
	
	
	
	public void addTranslateX(float x){
		this.x += x;
	}
	public void addTranslateY(float y){
		this.y += y;
	}
	
	public void onUpdate(){
		if(followingTarget){
			if(target != null){
				if(followStyle == FollowStyle.HARD){
					x = (float) (-target.getDrawX() + width / 2) * Age.ratioX();
					y = (float) (-target.getDrawY() + height / 2) * Age.ratioY();
				}else if(followStyle == FollowStyle.SOFT){
					float toX = (float) (x + (target.getDrawX() - width / 2) * Age.ratioX());
					float toY = (float) (y + (target.getDrawY() - height / 2) * Age.ratioY());
				
					double tan = Math.atan2(toX,toY);
					
					if(toX < 0){
						toX = -toX;
					}
					if(toY < 0){
						toY = -toY;
					}
				
					float biggest = 0;
					if(toX >= toY){
						biggest = toX;
					}else{
						biggest = toY;
					}
				
					float s = biggest / speed;//target.getSpeed() * ();
				
					double dX = s*Math.sin(tan);
					double dY = s*Math.cos(tan);
					if(toX > 5){
						x -= dX;
					}
					if(toY > 5){
						y -= dY;
					}
				}
			}
		}
		Render.all(this);
	}
	
	private enum FollowStyle{
		HARD, SOFT;
	}
	
}
