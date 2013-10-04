package com.gmail.robmadeyou.World;

import com.gmail.robmadeyou.Target;
import com.gmail.robmadeyou.Draw.Render;

public class Camera{
	private float camX, camY, x, y;
	private float camWidth, camHeight;
	private Target target;
	private boolean followingTarget = false;
	private float maxDistanceFromCamera = 10;
	private boolean cameraBounds = true;
	private String typeOfFollowing = "soft";
	private float speed;
	public Camera(float x, float y, float camX, float camY, float width, float height){
		this.x = x;
		this.y = y;
		this.camX = camX;
		this.camY = camY;
		camWidth = width;
		camHeight = height;
		this.speed = 20;
	}
	public boolean getCameraBounds(){
		return cameraBounds;
	}
	public float getCamX(){
		return x;
	}
	public float getCamY(){
		return y;
	}
	public float getX(){
		return camX;
	}
	
	public float getY(){
		return camY;
	}
	public float getWidth(){
		return camWidth;
	}
	
	public float getHeight(){
		return camHeight;
	}
	
	public void setCameraBounds(boolean args){
		this.cameraBounds = args;
	}
	
	public void setTarget(Target target){
		this.target = target;
	}
	
	public void setTypeOfFollowing(String type){
		this.typeOfFollowing = type;
	}
	public void setFollowSpeed(float speed){
		this.speed = speed;
	}
	
	public void setMaxDistanceFromCamera(float dist){
		this.maxDistanceFromCamera = dist;
	}
	
	public Target getTarget(){
		return target;
	}
	public float getMaxDistanceFromCamera(){
		return maxDistanceFromCamera;
	}
	
	public String getTypeOfFollowing(){
		return typeOfFollowing;
	}
	
	public void setFollowingTarget(boolean args){
		followingTarget = args;
	}
	
	public boolean isFollowingTarget(){
		return followingTarget;
	}
	public float getFollowSpeed(){
		return speed;
	}
	
	public void onUpdate(){
		if(isFollowingTarget()){
			if(typeOfFollowing.equals("hard")){
				hardMove();
			}else if(typeOfFollowing.equals("soft")){
				softMove();
			}
		}
		Math.round(camX);
		Math.round(camY);
		if(cameraBounds){
			if(camX > 0){
				camX = 0;
			}
			if(camY > 0){
				camY = 0;
			}
			if(-camX > World.getWorldWidthInPixels() - camWidth){
				camX = -World.getWorldWidthInPixels() + camWidth;
			}
			if(-camY > World.getWorldHeightInPixels() - camHeight){
				camY = -World.getWorldHeightInPixels() + camHeight;
			}
		}
		Render.renderAll(x, y, camX, camY, camWidth, camHeight);
	}
	public void hardMove(){
		camX = -target.getX() + camWidth / 2;
		camY = -target.getY() + camWidth / 2;
	}
	public void softMove(){
		float toX = camX + (target.getX() - camWidth / 2);
		float toY = camY + (target.getY() - camHeight / 2);
		
		double tan = Math.atan2(toX,toY);
		
		float targetSpeed = target.getSpeed();
		
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
		
		float multiplier = biggest / maxDistanceFromCamera;
		
		System.out.println("biggest" + biggest);
		System.out.println("y" + toY);
		float s = biggest / speed;//target.getSpeed() * ();
		
		double dX = s*Math.sin(tan);
		double dY = s*Math.cos(tan);
		if(toX > 5){
			camX -= dX;
		}
		if(toY > 5){
			camY -= dY;
		}
		
	}
	
}
