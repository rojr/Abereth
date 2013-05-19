package com.gmail.robmadeyou.Gui;

import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2f;

import static org.lwjgl.opengl.GL11.GL_QUADS;

import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Input.Mouse;
import com.gmail.robmadeyou.State.State;

public class Button implements Gui{

	private int x, y, width, height;
	private Texture tex;
	private Texture texHover;
	private Texture texPressed;
	private String state;
	private Color color;
	private int number;
	private String name;
	public Button(String name, int x, int y, int width, int height, Color color, Texture Texture, State state){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tex = Texture;
		this.texHover = Texture;
		this.texPressed = Texture;
		this.state = state.name();
		this.color = color;
		this.name = name;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setNumber(int num){
		this.number = num;
	}
	public void setHoverTexture(Texture texture){
		this.texHover = texture;
	}
	public void setPressedTexture(Texture texture){
		this.texPressed = texture;
	}
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void setColor(Color color){
		this.color = color;
	}
	public void setWidth(int w){
		this.width = w;
	}
	public void setHeight(int h){
		this.height = h;
	}
	public void setState(String state){
		this.state = state;
	}
	public void addToState(String toAdd){
		this.state.concat("," + toAdd);
	}
	public String getName(){
		return name;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public String getState(){
		return state;
	}
	public boolean isMouseOver(){
		int mX = Mouse.getX();
		int mY = Mouse.getY();
		if(mX > x && mX < x + width && mY > y && mY < y + height){
			return true;
		}
		return false;
	}
	public boolean isPressed(){
		if(isMouseOver() && Mouse.leftMouseButtonPressed){
			return true;
		}
		return false;
	}
	public boolean isReleasedOnButton(){
		if(isMouseOver() && Mouse.leftMouseButtonReleased){
			return true;
		}
		return false;
	}
	public void onUpdate(){
		/*
		 * Lalalalaallaalaaalalalal
		 */
		
		draw();
	}
	public void draw(){
		if(isPressed()){
			if(texPressed != null){
				texPressed.bind();
			}
		}else if(isMouseOver()){
			if(texPressed != null){
				texHover.bind();
			}
		}else{
			if(tex != null){
				tex.bind();
			}
		}
		if(color != null){
			color.bind();
		}else{
			Color.White.bind();
		}
		glBegin(GL_QUADS);
			glTexCoord2d(0, 0);
			glVertex2f(x , y);
			glTexCoord2d(1, 0);
			glVertex2f(x + width, y);
			glTexCoord2d(1, 1);
			glVertex2f(x + width, y + height);
			glTexCoord2d(0, 1);
			glVertex2f(x , y + height);
		glEnd();
	}
}
