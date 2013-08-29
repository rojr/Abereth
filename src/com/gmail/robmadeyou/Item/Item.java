package com.gmail.robmadeyou.Item;

import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Draw.Collector.DrawParameters;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Entity.Entity;
import com.gmail.robmadeyou.Input.Mouse;

public class Item {
	private double x, y;
	private int width, height;
	private int id;
	private int Texture;
	private int layer;
	
	public Item(double x, double y, int width, int height, int id, int Texture){
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.setTexture(Texture);
		this.layer = 1;
	}

	public int getLayer(){
		return layer;
	}
	public void setLayer(int layer){
		this.layer = layer;
	}
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTexture() {
		return Texture;
	}

	public void setTexture(int texture) {
		Texture = texture;
	}
	public boolean isNear(Entity other){
		int oX =(int) other.getX() + getWidth() / 2;
		int oY =(int) other.getY() + getHeight() / 2;

        return oX >= x && oX <= x + width && oY >= y && oY <= y + height;

    }
	
	public boolean isMouseOver(){
		int mX = Mouse.getX();
		int mY = Mouse.getY();
        return mX >= x && mX <= x + width && mY >= y && mY <= y + height;
    }
	
	public boolean isPressed(){
        return isMouseOver() && Mouse.isLeftMouseButtonPressed();
    }
	
	public boolean isHeld(){
        return Mouse.leftMouseButtonDown && isMouseOver();
    }
	
	public void onUpdate(){
		if(isHeld()){
			x += Mouse.getDX();
			y += Mouse.getDY();
		}
		//TODO work in progress
		//if(!World.isSolidAtLocation(x / World.BLOCK_SIZE(), y / World.BLOCK_SIZE()){
		//	
		//}
	}
	public void draw(){
		Collector.add(new DrawParameters("box", x, y, width, height, Texture, Color.White, 1, layer, true));
	}
}
