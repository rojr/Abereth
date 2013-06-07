package com.gmail.robmadeyou.Entity;

import com.gmail.robmadeyou.Block.Block;

public class Entity {
	
	private double x, y, dX, dY, speed, originalSpeed = 1,
					originalX, originalY, originalDX, originalDY;
	private int number,h,w, originalW, originalH, layer;
	
	public int getNumber(){
		return number;
	}
	public void setNumber(int num){
		this.number = num;
	}
	public void setWidth(int w){
		this.w = w;
	}
	public void setHeight(int h){
		this.h = h;
	}
	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
	}
	public void setDX(double dX){
		this.dX = dX;
	}
	public void setDY(double dY){
		this.dY = dY;
	}
	public void setSpeed(double speed){
		this.speed = speed;
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public double getDX(){
		return dX;
	}
	public double getDY(){
		return dY;
	}
	public double getSpeed(){
		return speed;
	}
	public int getWidth(){
		return w;
	}
	public int getHeight(){
		return h;
	}
	public void setLayer(int layer){
		this.layer = layer;
	}
	public void setOriginalWidth(int w){
		this.originalW = w;
	}
	public void setOriginalHeight(int h){
		this.originalH = h;
	}
	public void setOriginalX(double x){
		this.originalX = x;
	}
	public void setOriginalY(double y){
		this.originalY = y;
	}
	public void setOriginalDX(double dX){
		this.originalDX = dX;
	}
	public void setOriginalDY(double dY){
		this.originalDY = dY;
	}
	public void setOriginalSpeed(double speed){
		this.originalSpeed = speed;
	}
	public double getOriginalX(){
		return originalX;
	}
	public double getOriginalY(){
		return originalY;
	}
	public double getOriginalDX(){
		return originalDX;
	}
	public double getOriginalDY(){
		return originalDY;
	}
	public double getOriginalSpeed(){
		return originalSpeed;
	}
	public int getOriginalWidth(){
		return originalW;
	}
	public int getOriginalHeight(){
		return originalH;
	}
	public int getLayer(){
		return layer;
	}
	public Entity getType(){
		return this;
	}
	public void doEffectFromBlock(Block type){}
	public void removeEffectFromBlock(Block type){}
	public void onUpdate(int delta){}
	public void draw(){}
}
