package com.gmail.robmadeyou.Entity;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2d;

import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Gui.Fonts;
import com.gmail.robmadeyou.Input.Keyboard;
import com.gmail.robmadeyou.Input.Keyboard.Key;

public class Player implements Entity{
	private double x, y;
	private double dX, dY;
	private int width, height;
	private int number;
	private double speed;
	private boolean isJumping = false;
	private boolean isInAir = false;
	private double jumpDY = 0;
	final double finalJumpDY = 10;
	private MovementType movementType = MovementType.ARROW_KEYS;
	public Player(double x, double y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = 1;
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
	
	public void handleInput(int delta){
		int center = Screen.getWidth() / 2;
		int distFromSide = ((Screen.getWidth() - Screen.getWidth() / 5)) - center;
		double changedXAt = ((delta * speed - 0.8) /4) / ((x - Screen.translate_x) / (Screen.getWidth() - Screen.getWidth() / 5) - Screen.translate_x);
		System.out.println(changedXAt);
		if(movementType.equals(MovementType.ARROW_KEYS)){
			if(Keyboard.isKeyDown(Key.LeftArrow)){
				x -= (delta * (speed - 0.8));
				if(x <= (Screen.getWidth() / 5) - Screen.translate_x){
					Screen.translate_x += (delta * speed - 0.8) /4;
				}
				
			}
			if(Keyboard.isKeyDown(Key.RightArrow)){
				x += (delta * (speed - 0.8));
				Screen.translate_x -= changedXAt;
				if(x + width >= (Screen.getWidth() - Screen.getWidth() / 5) - Screen.translate_x){
					double bX = x;
					Screen.translate_x -= (delta * speed - 0.8) / 4;
					x = bX;
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
					y -= (delta * (speed - 0.8));
				}
				//TODO Update different game modes
			}
			if(Keyboard.isKeyDown(Key.DownArrow)){
				if(Screen.TypeOfGame == GameType.SIDE_SCROLLER){
				}
				if(Screen.TypeOfGame == GameType.RPG_STYLE){
					y += (delta * (speed - 0.8));
				}
			}
		}
	}
	
	
	public void onUpdate(int delta) {
		if(Screen.TypeOfGame == GameType.SIDE_SCROLLER){
			if(isJumping || isInAir){
				y -= jumpDY;
				jumpDY = jumpDY - 0.5;
				if(y + height > Screen.getHeight()){
					isJumping = false;
					jumpDY = 0;
				}
			}
			if(y + height < Screen.getHeight()){
				isInAir = true;
			}else{
				isInAir = false;
				jumpDY = 0;
			}
		}else if(Screen.TypeOfGame == GameType.RPG_STYLE){
			
		}
		handleInput(delta);
		draw();
	}
	public void draw() {
		glPushMatrix();
		glTranslatef(Screen.translate_x, Screen.translate_y, 0);
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
		
		glBegin(GL_QUADS);
		glTexCoord2d(0, 0);
		glVertex2d(900 , 40);
		glTexCoord2d(1, 0);
		glVertex2d(900 + width, 40);
		glTexCoord2d(1, 1);
		glVertex2d(900 + width, 40 + height);
		glTexCoord2d(0, 1);
		glVertex2d(900 , 40 + height);
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
