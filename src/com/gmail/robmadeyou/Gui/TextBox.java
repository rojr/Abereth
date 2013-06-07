package com.gmail.robmadeyou.Gui;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2i;

import org.newdawn.slick.opengl.Texture;

import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.Textures;
import com.gmail.robmadeyou.State.State;

public class TextBox implements Gui{

	private double x, y, size;
	private String text, state, name;
	private boolean hasBackground = false;
	private Texture backTex;
	private Color textColor;
	private int height, width, number;
	private int layer;
	public TextBox(String text, double x, double y, double size, State state, String name, Color textColor){
		this.text = text;
		this.x = x;
		this.y = y;
		this.size = size;
		this.state = state.name();
		this.name = name;
		this.textColor = textColor;
		this.backTex = Textures.none;
		this.layer = 0;
	}
	/*
	 * Set
	 */
	public void setLocation(int x, int y) {
		this.x = x;
	}
	public void setWidth(int w) {
		this.width = width;
	}
	public void setBackgroundVisibility(boolean b){
		this.hasBackground = b;
	}
	public void setBackTexture(Texture tex){
		this.backTex = tex;
	}
	public void setHeight(int height){
		this.height = height;
	}
	public void setNumber(int number){
		this.number = number;
	}
	public void setLayer(int layer){
		this.layer = layer;
	}
	/*
	 * Get
	 */
	public boolean hasBackground(){
		return hasBackground;
	}
	public int getNumber(){
		return number;
	}
	public String getName(){
		return name;
	}
	public String getState(){
		return state;
	}
	public int getHeight(){
		return height;
	}
	public int getX() {
		return (int)x;
	}
	public int getY() {
		return (int) 0;
	}
	public int getWidth() {
		return width;
	}
	public int getLayer(){
		return layer;
	}
	public void onUpdate() {
		drawBoxWithText();
	}
	public void addToState(String toAdd) {
		this.state.concat("," + toAdd);
	}
	private void drawBackground(){
		int width = Fonts.getWidth(text, size);
		int height = Fonts.getHeight(text, size);
		
		backTex.bind();
		glBegin(GL_QUADS);//ball.getY() thingy
			glTexCoord2d(0, 1);
			glVertex2i((int) x - (2 * (int)size),(int) y - (2 * (int)size)); //1
			glTexCoord2d(1, 1);
			glVertex2i((int) x - (2 * (int)size) + width + 10,(int) y - (2 * (int)size)); //2
			glTexCoord2d(1, 0);
			glVertex2i((int) x - (2 * (int)size)+ width + 10, (int) y - (2 * (int)size) + height + 10); //3
			glTexCoord2d(0, 0);
			glVertex2i((int) x - (2 * (int)size), (int) y - (2 * (int)size) + height + 10); //4
		glEnd();
	}
	public void drawBoxWithText(){
		if(hasBackground){
			drawBackground();
		}
		Fonts.drawString(text, (int) x + 10 * (int)size, (int) y, size, textColor);
	}
}
