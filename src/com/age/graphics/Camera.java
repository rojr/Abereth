package com.age.graphics;

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
		this.followStyle = FollowStyle.HARD;
		this.speed = 1;
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
	
	public void onUpdate(){
		if(followingTarget){
			if(target != null){
				if(followStyle == FollowStyle.HARD){
					x = (float) (-target.getDrawX() + width / 2);
					x = (float) (-target.getDrawY() + height / 2);
				}else if(followStyle == FollowStyle.SOFT){
					float toX = (float) (x + (target.getDrawX() - width / 2));
					float toY = (float) (y + (target.getDrawY() - height / 2));
				
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
						x -= dY;
					}
				}
			}
		}
		Render.all(this);
	}
	
	public void hardMove(){
		
	}
	public void softMove(){
		System.out.println("Hey");
		if(target != null){
			
		}
		
	}
	
	private enum FollowStyle{
		HARD, SOFT;
	}
	
}
