package com.age.graphics.ui;

import java.util.ArrayList;

import com.age.Age;
import com.age.graphics.render.Collector;
import com.age.graphics.render.shapes.Box;

public class Text{

	private String text;
	private ArrayList<Letter> list = new ArrayList<Letter>();
	private double textWidth, textHeight;
	private double x, y;
	public Text(String text, double x, double y) {
		this.text = text;
		this.x = x;
		this.y = y;
		int i = 0;
		int xPlus = 8;
		int yPlus = 0;
		addLetters();
	}
	
	private void addLetters(){
		//TODO Add fancy parameters for letters so that you can change the color from just the string
		//rather than having to change it constantly
		list.clear();
		
		int plusX = 0;
		int line = 0;
		for(char c : text.toCharArray()){
			int plusY = 0;
			if(c == 'g' || c == 'q' || c == 'p' || c == 'j' || c == 'y') {
				plusY += 1;
			}else if(c == '\n'){
				line++;
			}
			list.add(new Letter(c, x + plusX, y + plusY + (line * 8)));
			if(c == 'f' || c == 'l' || c == 't'){
				plusX += 7;
			}else if(c == 'i'){
				plusX += 5;
			}else{
				plusX += 8;
			}
		}
	}
	
	
	
	public void setText(String text){
		addLetters();
	}
	
	
	public void setTextWidth(double width){
		this.textWidth = width;
	}
	public void setTexTHeight(double height){
		this.textHeight = height;
	}
	
	
	
	public double getTextWidth(){
		return textWidth;
	}
	public double getTextHeight(){
		return textHeight;
	}
	
	
	
	public void render(){
		for(int j = 0; j < list.size(); j++){
			char c = list.get(j).getChar();
			for(int i = 0; i < Age.letters.length; i++){
				if(c == Age.letters[i]){
					list.get(j).setTexture(Age.letterTexID.get(i));
					list.get(j).render();
				}
			}
		}
	}
	
	public void setRotatation(int rotation){
		for(Letter l : list){
			l.setRotation(l.getRotation() + 1);
		}
	}
	
	private class Letter extends Box{
		
		private char c;
		private double x, y;
		public Letter(char c, double x, double y){
			super(x, y, 8, 8);
			this.c = c;
			this.x = x;
			this.y = y;
		}
		
		public char getChar(){
			return c;
		}
		public void render(){
			Collector.add(this);
		}
	}
}
