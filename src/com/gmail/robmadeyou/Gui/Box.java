package com.gmail.robmadeyou.Gui;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.newdawn.slick.opengl.Texture;

import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.State.State;

public class Box implements Gui {
	int x, y, width, height;
	private Texture tex;
	private Color color;
	private String name;
	private String state;
	private int number;
	private int layer;
	public Box(String name, int x, int y, int width, int height, Texture texture, Color color, State state){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.tex = texture;
		this.name = name;
		this.state = state.name();
		this.layer = 0;
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
	public void setLayer(int layer){
		this.layer = layer;
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
	
	public int getLayer(){
		return layer;
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
		com.gmail.robmadeyou.Draw.Box.drawBox(x, y, width, height);
	}
}