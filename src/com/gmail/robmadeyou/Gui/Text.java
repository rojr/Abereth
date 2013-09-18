package com.gmail.robmadeyou.Gui;

import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Draw.Collector.DrawParameters;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.TextureLoader;

import java.util.ArrayList;
import java.util.Random;

public class Text {

    private static char letters[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private static ArrayList<Integer> letterTexID = new ArrayList<Integer>();

    private static char numbers[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static ArrayList<Integer> numberTexID = new ArrayList<Integer>();

    public static void loadTextures() {
        for (int i = 0; i < letters.length / 2; i++) {
            letterTexID.add(TextureLoader.createTexture("res/font/sprite1.png", i * 8, 0, 8, 8));
        }
        for (int i = 0; i < letters.length / 2; i++) {
            letterTexID.add(TextureLoader.createTexture("res/font/sprite1.png", i * 8, 8, 8, 8));
        }
        for (int i = 0; i < numbers.length; i++) {
            numberTexID.add(TextureLoader.createTexture("res/font/sprite1.png", i * 8, 16, 8, 8));
        }
    }

    public static void drawString(String text, float x, double y, int layerID, float opacity, double size, Color color, boolean useTranslate, boolean inverts) {
        float originalX = x;
        boolean wasLastDigitModifier = false;
        boolean isThisCharModifier = false;
        boolean randomizeCharacter = false;
        boolean randomizeColor = false;
        
        for (char c : text.toCharArray()) {
        	if(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' ||c == '7' ||c == '8' ||c == '9' || c == 'r' || c == 'n'){
        		isThisCharModifier = true;
        	}else{
        		isThisCharModifier = false;
        	}
        	
        	if(wasLastDigitModifier && isThisCharModifier){
        		if(c == '0'){
        			color = Color.Peach_Puff;
        		}else if(c == '1'){
        			color = Color.Black;
        		}else if(c == '2'){
        			color = Color.White;
        		}else if(c == '3'){
        			color = Color.Red;
        		}else if(c == '4'){
        			color = Color.Blue;
        		}else if(c == '5'){
        			color = Color.Green;
        		}else if(c == '6'){
        			color = Color.Yellow;
        		}else if(c == '7'){
        			color = Color.Pink;
        		}else if(c == '8'){
        			color = Color.Purple;
        		}else if(c == '9'){
        			color = Color.Banana;
        		}else if(c == 'r'){
        			randomizeCharacter = true;
        			System.out.println("yes");
        		}else if(c == 'n'){
        			randomizeCharacter = false;
        		}
        	}else{
        		for(int i = 0; i < letters.length; i++) {
        			if(c == letters[i]) {
        				if (c == 'g' || c == 'q' || c == 'p' || c == 'j' || c == 'y') {
        					y += 1;
        				}
        				Random ran;
        				if(randomizeCharacter){
        					ran = new Random();
        					int ranID = ran.nextInt(letterTexID.size());
        					ranID = letterTexID.get(ranID);
        					Collector.add(new DrawParameters("box", x, y, 8, 8, ranID, color, opacity, layerID, useTranslate, inverts));
        				}else{
        					Collector.add(new DrawParameters("box", x, y, 8, 8, letterTexID.get(i), color, opacity, layerID, useTranslate, inverts));
        				}
        				if(c == 'g' || c == 'q' || c == 'p' || c == 'j' || c == 'y') {
        					y -= 1;
        				}
        				if(c == 'f' || c == 'l' || c == 't') {
        					x += 7;
        				}else if (c == 'i') {
        					x += 5;
        				}else {
        					x += 8;
        				}
        				
        			}
        		}
        		if(c == ' ') {
    				x += 8;
    			}else if (c == '\n') {
    				x = originalX;
    				y += 9;
    			}
        		for(int i = 0; i < numbers.length; i++) {
        			if(c == numbers[i]) {
        					Collector.add(new DrawParameters("box", x, y, 8, 8, numberTexID.get(i), color, opacity, layerID, useTranslate, inverts));
        					x += 8;
        			}
        		}
        	}
        	if(c == '^'){
        		wasLastDigitModifier = true;
    		}else{
    			wasLastDigitModifier = false;
    		}
        }
    }
}
