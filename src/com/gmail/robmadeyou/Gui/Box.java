package com.gmail.robmadeyou.Gui;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.newdawn.slick.opengl.Texture;

import com.gmail.robmadeyou.Effects.Color;

public class Box implements Gui {
	int x, y, width, height;
	private Texture tex;
	private Color color;
	private String name, state;
	private int number;
	public Box(String name, int x, int y, int width, int height, Texture texture, Color color, String state){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.tex = texture;
		this.name = name;
		this.state = state;
	}
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setState(String state){
		this.state = state;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void addToState(String toAdd){
		this.state.concat("," + toAdd);
	}
	
	public void setWidth(int w) {
		this.width = w;
	}
	
	public void setHeight(int h) {
		this.height = h;
	}
	
	public void setNumber(int number){
		this.number = number;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getName(){
		return name;
	}
	
	public String getState(){
		return state;
	}
	
	public int getNumber(){
		return number;
	}
	public void onUpdate() {
		draw();
	}
	public void draw(){
		if(tex != null){
			if(tex != null){
				tex.bind();
			}
		}
		color.bind();
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
