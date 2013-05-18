package com.gmail.robmadeyou.Gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.Textures;
import com.gmail.robmadeyou.State.State;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;


import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_LINES;

public class InputBox implements Gui{
	
	private String text, name;
	private String state;
	private int x,y,w,h;
	private int number;
	private Color color;
	private boolean isSelected;
	private int maxCharacters;
	public InputBox(int x, int y,int w, int h,String name, Color color, State state, int maxCharacters){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.state = state.name();
		this.name = name;
		this.color = color;
		this.text = "";
		this.maxCharacters = maxCharacters;
	}
	/*
	 * Setters
	 */
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void setWidth(int w) {
		this.w = w;
	}
	public void setHeight(int h) {
		this.h = h;
	}
	
	/*
	 * Getters
	 */
	
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getWidth(){
		return w;
	}
	public int getHeight(){
		return h;
	}
	public String getState(){
		return state;
	}
	public String getText(){
		return text;
	}
	public String getName(){
		return name;
	}
	public int getNumber(){
		return number;
	}
	public void setNumber(int number){
		this.number = number;
	}
	public void addToState(String toAdd){
		this.state.concat("," + toAdd);
	}
	public void onUpdate(){
		draw();
		int mX = Mouse.getX();
		int mY = Display.getHeight() - Mouse.getY();

		//Had to change this because the While(Mouse.next()) method is already being used by buttons, so it gets kind of buggy :/
		if(Mouse.isButtonDown(0)){
			isSelected = false;
			if(mX >= x && mX <= x + w && mY >= y && mY <= y + h){
				isSelected = true;
			}else{
				isSelected = false;
			}
		}
		if(isSelected && text.length() < maxCharacters){
			while(Keyboard.next()){
				if(Keyboard.getEventKeyState()){
					if(Keyboard.isKeyDown(Keyboard.getEventKey())){
						for(int i = 0; i < Fonts.alphabet.length; i++){
							if(Keyboard.getEventCharacter() == Fonts.alphabet[i]){
								text += Keyboard.getEventCharacter();
							}
						}
						for(int i = 0; i < Fonts.symbols.length; i++){
							if(Keyboard.getEventCharacter() == Fonts.symbols[i]){
								text += Keyboard.getEventCharacter();
							}
						}
						if(Keyboard.getEventCharacter() == '.'){
							text += '.';
						}
					}
					if(Keyboard.isKeyDown(Keyboard.KEY_BACK)){
						if(text.length() >= 1){
							text = text.substring(0, text.length() - 1);
						}
					}
					if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
						text += " ";
					}
				}
			}
		}
	}
	public void draw(){
		Textures.none.bind();
		if(isSelected){
			Color.White.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(0,0);
				glVertex2i(x, y);
				glTexCoord2f(1,0);
				glVertex2i(x + w, y);
				glTexCoord2f(1,1);
				glVertex2i(x + w, y + h);
				glTexCoord2f(0,1);
				glVertex2i(x, y + h);
			glEnd();
		}else{
			color.bind();
			glBegin(GL_LINES);
				glTexCoord2f(0,0);
				glVertex2i(x, y);
				glTexCoord2f(1,1);
				glVertex2i(x + w, y);
			glEnd();

			glBegin(GL_LINES);
				glTexCoord2f(0,0);
				glVertex2i(x + w, y);
				glTexCoord2f(1,1);
				glVertex2i(x + w, y + h);
			glEnd();

			glBegin(GL_LINES);
				glTexCoord2f(0,0);
				glVertex2i(x + w, y + h);
				glTexCoord2f(1,1);
				glVertex2i(x, y + h);
			glEnd();

			glBegin(GL_LINES);
				glTexCoord2f(0,0);
				glVertex2i(x, y + h);
				glTexCoord2f(1,1);
				glVertex2i(x, y);
			glEnd();
		}
		Fonts.drawString(text, x, y, 1, color);
	}
}
