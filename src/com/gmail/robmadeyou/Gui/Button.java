package com.gmail.robmadeyou.Gui;

import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Draw.Collector.DrawParameters;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Input.Mouse;

public class Button implements Gui{
	private double x,y;
	private int width,height,layer, texture;
	private float opacity;
	private boolean useTranslate;
	private boolean isPressed,isReleased,isOver;
	public Button(double x, double y, int width, int height, int layer){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.layer = layer;
		this.texture = -1;
		this.opacity = 1f;
		this.useTranslate = false;
	}
	
	public void setX(double x){
		this.x = x;
	}

	public void setY(double y){
		this.y = y;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public void setLayer(int layer){
		this.layer = layer;
	}
	
	public void setTexture(int texture){
		this.texture = texture;
	}
	
	public void useTranslate(boolean args){
		this.useTranslate = args;
	}
	
	public void setOpacity(float opacity){
		this.opacity = opacity;
	}

	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}

	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}

	public int getLayer(){
		return layer;
	}

	public boolean isMouseOver(){
		isOver = false;
		int x2 = Mouse.getX();
		int y2 = Mouse.getY();
		if(x2 >= x && x2 <= x + width && y2 >= y && y2 <= y + height){
			isOver = true;
			return true;
		}
		return false;
	}
	
	public boolean isPressed(){
		isPressed = false;
		if(isMouseOver()){
			if(Mouse.leftMouseButtonDown){
				isPressed = true;
				return true;
			}
		}
		return false;
	}
	
	public boolean isReleased(){
		isReleased = false;
		if(isReleased == true && !isPressed()){
			isReleased = true;
			return true;
		}
		return false;
	}
	
	public void draw(){
		Collector.add(new DrawParameters("box", x, y, width, height, texture, Color.White, opacity, layer, useTranslate));
	}
}
