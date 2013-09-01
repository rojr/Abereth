package com.gmail.robmadeyou.World;

import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Target;
import com.gmail.robmadeyou.Input.Mouse;

public class Camera {
	private double camX;
	private double camY;
	private int camWidth, camHeight;
	private Target target;
	private boolean followingTarget = false;
	private float maxDistanceFromCamera = 300;
	private String typeOfFollowing = "soft";
	public Camera(double x, double y){
		camX = x;
		camY = y;
		camWidth = Screen.getWidth();
		camHeight = Screen.getHeight();
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
		if(typeOfFollowing.equals("hard")){
			hardMove();
		}else if(typeOfFollowing.equals("soft")){
			softMove();
		}
	}
	public void hardMove(){
		Screen.translate_x = -target.getX() + camWidth / 2;
		Screen.translate_y = -target.getY() + camWidth / 2;
	}
	public void softMove(){
		double toX = Screen.translate_x + (target.getX() - camWidth / 2);
		double toY = Screen.translate_y + (target.getY() - camHeight / 2);
		
		
		if(toX < 2 && toX > -2){
			toX = 0;
			Screen.translate_x = -(target.getX() - camWidth / 2);
		}
		if(toY < 2 && toY > -2){
			toY = 0;
			Screen.translate_y = -(target.getY() - camHeight / 2);
		}
		
		System.out.println("x; " + toX + "  y:  " + toY);
		
		
		double s = 3.8;
		double tan = Math.atan2(toX,toY);
		
		System.out.println(tan);
		if(tan != Math.PI){
			double dX = s*Math.sin(tan);
			double dY = s*Math.cos(tan);
			
			Screen.translate_x -= dX;
			Screen.translate_y -= dY;
		}
		
	}
	
}
