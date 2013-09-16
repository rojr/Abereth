package com.gmail.robmadeyou.World;

import com.gmail.robmadeyou.Target;
import com.gmail.robmadeyou.Draw.Render;

public class Camera{
	private double camX, camY, x, y;
	private double camWidth, camHeight;
	private Target target;
	private boolean followingTarget = false;
	private float maxDistanceFromCamera = 10;
	private boolean cameraBounds = true;
	private String typeOfFollowing = "soft";
	public Camera(double x, double y, double camX, double camY, double width, double height){
		this.x = x;
		this.y = y;
		this.camX = camX;
		this.camY = camY;
		camWidth = width;
		camHeight = height;
	}
	public boolean getCameraBounds(){
		return cameraBounds;
	}
	public double getCamX(){
		return x;
	}
	public double getCamY(){
		return y;
	}
	public double getX(){
		return camX;
	}
	
	public double getY(){
		return camY;
	}
	public double getWidth(){
		return camWidth;
	}
	
	public double getHeight(){
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
	
	public void onUpdate(){
		Render.renderAll(x, y, camX, camY, camWidth, camHeight);
		
		if(isFollowingTarget()){
			if(typeOfFollowing.equals("hard")){
				hardMove();
			}else if(typeOfFollowing.equals("soft")){
				softMove();
			}
		}
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
	}
	public void hardMove(){
		camX = -target.getX() + camWidth / 2;
		camY = -target.getY() + camWidth / 2;
	}
	public void softMove(){
		double toX = camX + (target.getX() - camWidth / 2);
		double toY = camY + (target.getY() - camHeight / 2);
		
		double tan = Math.atan2(toX,toY);
		
		double targetSpeed = target.getSpeed();
		
		if(toX < 0){
			toX = -toX;
		}
		if(toY < 0){
			toY = -toY;
		}
		
		double biggest = 0;
		if(toX >= toY){
			biggest = toX;
		}else{
			biggest = toY;
		}
		
		double multiplier = biggest / maxDistanceFromCamera;
		
		double s = 3;//target.getSpeed() * ();
		
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
