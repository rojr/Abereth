package com.age.graphics.ui;

import java.util.ArrayList;

import com.age.Age;
import com.age.graphics.render.Collector;
import com.age.graphics.render.shapes.Box;

public class Text{

	private String text;
	private ArrayList<Letter> list = new ArrayList<Letter>();
	public Text(String text, double x, double y) {
		this.text = text;
		int i = 0;
		for(char c :  text.toCharArray()){
			list.add(new Letter(c, x + 8 * i, y));
			i++;
		}
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
