package com.age.graphics.ui;

import java.util.ArrayList;

import com.age.Age;
import com.age.graphics.Drawable;
import com.age.graphics.DrawableGroup;
import com.age.graphics.render.Collector;
import com.age.graphics.render.shapes.Box;

public class Text extends DrawableGroup{

	private String text;
	private ArrayList<Letter> list = new ArrayList<Letter>();
	private double textWidth, textHeight;
	private double x, y;
	public Text(String text, double x, double y){
		this.text = text;
		this.x = x;
		this.y = y;
		int i = 0;
		int xPlus = 8;
		int yPlus = 0;
		addLetters();
	}
	
	public void setLocation(double x, double y){
		for(Letter l : list){
			l.setX(x);
			l.setY(y);
		}
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
			list.add(new Letter(c, x, y, plusX,plusY + (line * 8)));
			if(c == 'f' || c == 'l' || c == 't'){
				plusX += 7;
			}else if(c == 'i'){
				plusX += 5;
			}else{
				plusX += 8;
			}
		}
		setList(list);
	}
	
	
	
	public void setText(String text){
		this.text = text;
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
			for(int i = 0; i < Age.numbers.length; i++){
				if(c == Age.numbers[i]){
					list.get(j).setTexture(Age.numberTexID.get(i));
					list.get(j).render();
				}
			}
		}
	}
	//Holy crap, I named this setRotatation
	public void setRotation(int rotation){
		for(Letter l : list){
			l.setRotation(rotation);
		}
	}
	public void setRotation(int rotation, int index) throws IndexOutOfBoundsException{
		if(index >= list.size()){
			throw new IndexOutOfBoundsException("Not enough letters for such big numbers! (Meaning the index you inputted does not exist)");
		}else{
			list.get(index).setRotation(rotation);
		}
	}
	
	private class Letter extends Box{
		
		private char c;
		private double x, y;
		private double plusX, plusY;
		public Letter(char c, double x, double y, double plusX, double plusY){
			super(x + plusX, y + plusY, 8, 8);
			this.c = c;
			this.x = x + plusX;
			this.y = y + plusY;
			this.plusX = plusX;
			this.plusY = plusY;
		}
		
		public void setX(double x){
			setDrawX(x + plusX);
		}
		public void setY(double y){
			setDrawY(y + plusY);
		}
		
		public char getChar(){
			return c;
		}
		public void render(){
			Collector.add(this);
		}
	}
}
