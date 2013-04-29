package com.gmail.robmadeyou.Gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2f;

import static org.lwjgl.opengl.GL11.GL_QUADS;

import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Input.Mouse;

public class Button implements Gui{

	private int x, y, width, height;
	private Texture tex;
	private Texture texHover;
	private Texture texPressed;
	private String state;
	private Color color;
	private int number;
	private boolean isMouseOver;
	private boolean isPressed;
	private boolean isReleasedOnButton;
	public Button(int x, int y, int width, int height, Color color, Texture Texture, String State){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tex = Texture;
		this.texHover = Texture;
		this.texPressed = Texture;
		this.state = State;
		this.color = color;
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
			isMouseOver = true;
			return true;
		}
		isMouseOver = false;
		return false;
	}
	public boolean isPressed(){
		if(isMouseOver && Mouse.leftMouseButtonPressed){
			isPressed = true;
			return true;
		}
		isPressed = false;
		return false;
	}
	public boolean isReleasedOnButton(){
		if(isMouseOver && Mouse.leftMouseButtonReleased){
			isReleasedOnButton = true;
			return true;
		}
		isReleasedOnButton = false;
		return false;
	}
	public void onUpdate(){
		/*
		 * Lalalalaallaalaaalalalal
		 */
		draw();
	}
	public void draw(){
		//TODO Add notifications that tell if the textures are not loading or something has gone wrong
		if(isPressed){
			if(texPressed != null){
				texPressed.bind();
			}
		}else if(isMouseOver){
			if(texPressed != null){
				texHover.bind();
			}
		}else{
			if(tex != null){
				tex.bind();
			}
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
