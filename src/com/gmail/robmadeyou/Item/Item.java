package com.gmail.robmadeyou.Item;

import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Draw.Collector.DrawParameters;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Entity.Entity;
import com.gmail.robmadeyou.Gui.Gui;
import com.gmail.robmadeyou.Input.Mouse;
import com.gmail.robmadeyou.World.World;

public class Item {
	private double x, y;
	private int width, height;
	private int id;
	private int Texture;
	private int layer;
	private boolean wasPressed = false;
	private boolean isInGui = false;
	private Gui inGui = null;
	
	
	//Possibly making some gravity effect
	//when the items are let go, they slow down
	//instead of just falling straight down
	
	//private int lastDX = 0, lastDY = 0;
	//private double leftDX = 0, leftDY = 0;
	
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
		
		if(oX >= x && oX <= x + width && oY >= y && oY <= y + height){
			return true;
		}
		
		return false;
	}
	
	public boolean isMouseOver(){
		int mX = Mouse.getX();
		int mY = Mouse.getY();
		if(mX >= x && mX <= x + width && mY >= y && mY <= y + height){
			return true;
		}
		return false;
	}
	
	public boolean isPressed(){
		if(isMouseOver() && Mouse.isLeftMouseButtonPressed()){
			return true;
		}
		return false;
	}
	/*
	 * Boolean checking if the item is being held, only returned true if the mouse is
	 * inside the item, this can be tricky to work with as the mouse would move too 
	 * fast from the item and the mouse would end up not being inside the bounds of the item
	 * use isDragged better;
	 */
	public boolean isHeld(){
		if(Mouse.leftMouseButtonDown && isMouseOver()){
			wasPressed = true;
			return true;
		}
		return false;
	}
	
	public boolean isDragged(){
		isHeld();
		if(!Mouse.leftMouseButtonDown && wasPressed){
			wasPressed = false;
			System.out.println(Mouse.getDX());
			return false;
		}
		if(wasPressed){
			return true;
		}
		return false;
	}
	
	public void onUpdate(){
		if(isDragged()){
			x += Mouse.getDX();
			y += Mouse.getDY();
		}else{
			if(!World.isSolidAtLocation((int)Math.round(x / World.BLOCK_SIZE()), (int)Math.round(y / World.BLOCK_SIZE()))){
				y += World.gravity(Screen.delta) * 10;
			}
		}
	}
	
	public void draw(){
		Collector.add(new DrawParameters("box", x, y, width, height, Texture, Color.White, 1, layer, true));
	}
}
