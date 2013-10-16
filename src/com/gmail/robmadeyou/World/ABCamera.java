package com.gmail.robmadeyou.world;

import com.gmail.robmadeyou.ABTarget;
import com.gmail.robmadeyou.Abereth;
import com.gmail.robmadeyou.draw.ABRender;

public class ABCamera{
	private float camX, camY, x, y;
	private float camWidth, camHeight;
	private ABTarget target;
	private boolean followingTarget = false;
	private float maxDistanceFromCamera = 10;
	private boolean cameraBounds = true;
	private String typeOfFollowing = "soft";
	private float speed;
	public ABCamera(float x, float y, float camX, float camY, float width, float height){
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
	
	public void setTarget(ABTarget target){
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
	
	public ABTarget getTarget(){
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
	public ABCamera verticalSplit(){
		ABCamera cam = new ABCamera(x + camWidth / 2, y, camX, camY, camWidth / 2, camHeight);
		
		camWidth /= 2;
		x /= 2;
		
		
		return Abereth.addNewCamera(cam);
	}
	public ABCamera horizontalSplit(){
		ABCamera cam = new ABCamera(x, y + camHeight / 2, camX, camY, camWidth, camHeight / 2);
		camHeight /= 2;
		y /= 2;
		
		return Abereth.addNewCamera(cam);
	}
	
	public void onUpdate(){
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
			if(-camX > ABWorld.getWorldWidthInPixels() - camWidth){
				camX = -ABWorld.getWorldWidthInPixels() + camWidth;
			}
			if(-camY > ABWorld.getWorldHeightInPixels() - camHeight){
				camY = -ABWorld.getWorldHeightInPixels() + camHeight;
			}
		}
		ABRender.renderAll(x, y, camX, camY, camWidth, camHeight);
	}
	public void hardMove(){
		camX = -target.getX() + camWidth / 2;
		camY = -target.getY() + camWidth / 2;
	}
	public void softMove(){
		float toX = camX + (target.getX() - camWidth / 2);
		float toY = camY + (target.getY() - camHeight / 2);
		
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
			camX -= dX;
		}
		if(toY > 5){
			camY -= dY;
		}
		
	}
	
}
