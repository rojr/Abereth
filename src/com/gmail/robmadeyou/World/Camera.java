package com.gmail.robmadeyou.World;

import com.gmail.robmadeyou.Target;
import com.gmail.robmadeyou.Draw.Render;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

public class Camera{
	private double camX, camY, x, y;
	private double camWidth, camHeight;
	private Target target;
	private boolean followingTarget = false;
	private float maxDistanceFromCamera = 300;
	private String typeOfFollowing = "soft";
	public Camera(double x, double y, double camX, double camY, double width, double height){
		this.x = x;
		this.y = y;
		this.camX = camX;
		this.camY = camY;
		camWidth = width;
		camHeight = height;
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
	public void hardMove(){
		camX = -target.getX() + camWidth / 2;
		camY = -target.getY() + camWidth / 2;
	}
	public void softMove(){
		double toX = camX + (target.getX() - camWidth / 2);
		double toY = camY + (target.getY() - camHeight / 2);
		
		
		if(toX <= 4 && toX >= -4){
			toX = 0;
			camX = -(target.getX() - camWidth / 2);
		}
		if(toY <= 4 && toY >= -6){
			toY = 0;
			camY = -(target.getY() - camHeight / 2);
		}
		
		double s = 6;//target.getSpeed() * ();
		double tan = Math.atan2(toX,toY);
		System.out.println(toX);
		System.out.println(toY);
		double dX = s*Math.sin(tan);
		double dY = s*Math.cos(tan);
			
		camX -= dX;
		camY -= dY;
		
	}
	
}
