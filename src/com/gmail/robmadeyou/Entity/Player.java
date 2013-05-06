package com.gmail.robmadeyou.Entity;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex2d;

import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.World.World;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Gui.Fonts;
import com.gmail.robmadeyou.Input.Keyboard;
import com.gmail.robmadeyou.Input.Keyboard.Key;

public class Player implements Entity{
	private double originalX, originalY, x, y;
	private double dX, dY;
	private int originalWidth, originalHeight, width, height;
	private int crouchHeight;
	private int number;
	private double speed;
	private boolean isJumping = false;
	private boolean isInAir = false;
	private boolean isCrouching = false;
	private double jumpDY = 0;
	private boolean hasClicked = false;
	final double finalJumpDY = 7;
	private MovementType movementType = MovementType.ARROW_KEYS;
	public Player(double x, double y, int width, int height){
		this.x = x;
		this.y = y;
		this.originalX = x;
		this.originalY = y;
		this.originalHeight = height;
		this.originalWidth = width;
		this.width = width;
		this.height = height;
		this.speed = 1;
		this.crouchHeight = (height / 4) * 3;
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
	public void setFixedMovementType(MovementType type){
		this.movementType = type;
	}
	public void setNumber(int num){
		this.number = num;
	}
	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
	}
	public void setWidth(int w) {
		this.width = w;
		
	}
	public void setHeight(int h) {
		this.height = h;
	}
	public double getX(){
		return x;
	}
	public int getNumber(){
		return number;
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
	public MovementType getMovementType(){
		return movementType;
	}
	public double getDX() {
		return dX;
	}
	public double getDY() {
		return dY;
	}
	public double getSpeed() {
		return speed;
	}
	public Key getUpKey(MovementType type){
		//TODO DO ARROW KEYS AND ALL THAT SHINANIGANS
		return Key.UpArrow;
	}
	public void handleInput(int delta){
		if(movementType.equals(MovementType.ARROW_KEYS)){
			if(Keyboard.isKeyDown(Key.LeftArrow)){
				if(!World.isSolidLeft(this)){
					x -= (delta * (speed - 0.8));
				}
				if(x + width <= (Screen.getWidth() / 5) - Screen.translate_x){
					Screen.translate_x += (delta * (speed - 0.8));
				}
				
			}
			if(Keyboard.isKeyDown(Key.RightArrow)){
				if(!World.isSolidRight(this)){
					x += (delta * (speed - 0.8));
				}
				if(x + width >= (Screen.getWidth() - (Screen.getWidth() / 5)) - Screen.translate_x){
					Screen.translate_x -= (delta * (speed - 0.8));
				}
			}
			if(Keyboard.isKeyDown(Key.UpArrow)){
				if(Screen.TypeOfGame == GameType.SIDE_SCROLLER){
					if(!isJumping){
						isJumping = true;
						jumpDY = finalJumpDY;
					}
				}
				if(Screen.TypeOfGame == GameType.RPG_STYLE){
					if(!World.isSolidAbove(this)){
						y -= (delta * (speed - 0.8));
					}
				}
				//TODO Update different game modes
			}
			if(Keyboard.isKeyDown(Key.DownArrow)){
				if(Screen.TypeOfGame == GameType.SIDE_SCROLLER){
					isCrouching = true;
				}
				if(Screen.TypeOfGame == GameType.RPG_STYLE){
					if(World.isSolidUnder(this)){
						y += (delta * (speed - 0.8));
					}
				}
			}else{
				if(Screen.TypeOfGame == GameType.SIDE_SCROLLER){
					isCrouching = false;
				}
			}
		}
	}
	
	
	public void onUpdate(int delta) {
		System.out.println(isJumping + "   " + isInAir + " ABOVE:" + World.isSolidAbove(this) + "    BELOW: " + World.isSolidUnder(this));
		if(Screen.TypeOfGame == GameType.SIDE_SCROLLER){
			if(isJumping || isInAir){
				y -= jumpDY * (delta * 0.1);
				jumpDY = jumpDY - 0.5;
				if(y + height > Screen.getHeight()){
					isJumping = false;
					jumpDY = 0;
				}
				if(World.isSolidUnder(this)){
					isJumping = false;
					jumpDY = 0;
				}
				if(World.isSolidAbove(this)){
					isJumping = true;
					isInAir = true;
				}
			}
			
			if(y + height < Screen.getHeight()){
				isInAir = true;
			}else{
				isInAir = false;
				jumpDY = 0;
			}
			
			if(isCrouching){
				if(hasClicked){
					height = crouchHeight - 3;
					y += ((originalHeight - crouchHeight) * 4) + + 3;
					hasClicked = false;
				}
			}else{
				if(!World.isSolidAbove(this)){
					if(!hasClicked){
						y -= ((originalHeight - crouchHeight) * 4) + 8;
						height = originalHeight * 2;
						hasClicked = true;
					}
				}
			}
		}else if(Screen.TypeOfGame == GameType.RPG_STYLE){
			
		}
		handleInput(delta);
		draw();
	}
	public void draw() {
		glPushMatrix();
		glTranslated(Screen.translate_x, Screen.translate_y, 0);
		Fonts.drawString("Player",(int) x - 5,(int) y - 20, 1, Color.Red);
		glBegin(GL_QUADS);
			glTexCoord2d(0, 0);
			glVertex2d(x , y);
			glTexCoord2d(1, 0);
			glVertex2d(x + width, y);
			glTexCoord2d(1, 1);
			glVertex2d(x + width, y + height);
			glTexCoord2d(0, 1);
			glVertex2d(x , y + height);
		glEnd();
		
		glPopMatrix();
	}
	public enum MovementType{
		ARROW_KEYS(),
		WASD_KEYS(),
		NUMPAD_KEYS(),
		IJKL_KEYS();
		MovementType(){
		}
	}
}
