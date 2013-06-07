package com.gmail.robmadeyou.Gui;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.Textures;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2d;
public class Fonts {

	static ArrayList<Texture> letterTexList = new ArrayList<Texture>();
	static ArrayList<Texture> symbolTexList = new ArrayList<Texture>();
	static char alphabet[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
								'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
								'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
								'k', 'l', 'm', 'n', 'o', 'p', 'q','r', 's', 't', 'u', 'v',
								'w', 'x', 'y', 'z'};
	static char symbols[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	//Goes through the loop and sets up all the textures(All fonts are saved similarly
	//and according to what letter is of what number
	public static boolean texSetUp = false;

	public static void drawCharacter(char c, double x, double y, double size){
		for(int i = 0; i < alphabet.length; i++){
			if(c == alphabet[i]){
				float width = letterTexList.get(i).getImageWidth();
				float height = letterTexList.get(i).getImageHeight();

				//the physical width of the texture which will be used in glTexCoord (generally a float between 0 and 1)
				float textureWidth = letterTexList.get(i).getWidth();
			    float textureHeight = letterTexList.get(i).getHeight();
				//texture offsets, for texture atlas purposes. leave at 0 for full image
				float textureOffsetX = 0;
				float textureOffsetY = 0;

				letterTexList.get(i).bind();
				glBegin(GL_QUADS);
					glTexCoord2f(textureOffsetX, textureOffsetY + textureHeight);
					glVertex2d(x, y);
					glTexCoord2f(textureOffsetX + textureWidth, textureOffsetY + textureHeight);
					glVertex2d(x + width * size, y);
					glTexCoord2f(textureOffsetX + textureWidth, textureOffsetY);
					glVertex2d(x + width * size, y + height * size);
					glTexCoord2f(textureOffsetX, textureOffsetY);
					glVertex2d(x, y + height * size);
				glEnd();
			}
		}
		//Need 2 for statements because the loop goes through the size of the of the
		//array to see if the character equals the one set in array list
		for(int i = 0; i < symbols.length; i++){
			if(c == symbols[i]){

				float width = symbolTexList.get(i).getImageWidth();
				float height = symbolTexList.get(i).getImageHeight();

				//the physical width of the texture which will be used in glTexCoord (generally a float between 0 and 1)
				float textureWidth = symbolTexList.get(i).getWidth();
				float textureHeight = symbolTexList.get(i).getHeight();
				//texture offsets, for texture atlas purposes. leave at 0 for full image
				float textureOffsetX = 0;
				float textureOffsetY = 0;

				symbolTexList.get(i).bind();
				glBegin(GL_QUADS);
					glTexCoord2f(textureOffsetX, textureOffsetY + textureHeight);
					glVertex2d(x, y);
					glTexCoord2f(textureOffsetX + textureWidth, textureOffsetY + textureHeight);
					glVertex2d(x + width * size, y);
					glTexCoord2f(textureOffsetX + textureWidth, textureOffsetY);
					glVertex2d(x + width * size, y + height * size);
					glTexCoord2f(textureOffsetX, textureOffsetY);
					glVertex2d(x, y + height * size);
				glEnd();
			}
		}
	}

	public static void setUpTextures(){
		if(!texSetUp){
			for(int i = 0; i < alphabet.length; i++){
				letterTexList.add(null);
				try {
					letterTexList.set(i, TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/font/a" + i + ".png"), true));
				} catch (IOException e) {
				}
			}
			for(int i = 0; i < symbols.length; i++){
				symbolTexList.add(null);
				try {
					symbolTexList.set(i, TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/font/s" + i + ".png"), true));
				} catch (IOException e) {
				}
			}
			texSetUp = true;
		}
	}

	/*
	 * Statement that is called that loops through every letter in the string
	 * and is the actual thing that will calculate all the spaces needed and 
	 * location of the next textured quad
	 */
	public static void drawString(String text, int x, int y, double size, Color color){
		int startX = x;
		color.bind();
		for(char c : text.toCharArray()){
			if(c != ' ' && c != '\n'){
				//If statement to check if letters need to be moved one down (look
				//bad if not done so I did :p
				if(c == 'y' || c == 'g' || c == 'j' || c == 'p'){
					drawCharacter(c, (double) x, (double)y + 1 * size, size);
				}else{
					drawCharacter(c, (double) x, (double)y, size);
				}
				if(c != 'i' && c != 'l'){
					x += 6 * size;
				}
				if(c == 'i'){
					x += 3 * size;
				}else if(c == 'l'){
					x += 4 * size;
				}
			}
			if(c == ' '){
				x += 8 * size;
			}else if(c == '\n'){
				x = startX;
				y += 7 * size;
			}
		}
		Textures.none.bind();
		Color.White.bind();
	}

	public static int getWidth(String text, double size){
		int x = 0;
		int largestX = 0;
		for(char c : text.toCharArray()){
			if(c != 'i' && c != 'l'){
				x += 6 * size;
			}
			if(c == 'i'){
				x += 3 * size;
			}else if(c == 'l'){
				x += 4 * size;
			}
			if(c == ' '){
				x += 8 * size;
			}
			if(c == '\n'){
				x = 0;
			}
			/*
			 * Some simple maths in case the \n was used, if it wasn't for the below
			 * if statement the width would just keep adding according to the amount
			 * of characters there were in the string, so if \n is present in the for 
			 * statement then x is set to 0 yet largestX still remains the same :p
			 */
			if(largestX <= x){
				largestX = x;
			}
		}
		
		return largestX;
	}

	public static int getHeight(String text, double size){
		int y = (int) (7 * size);
		for(char c : text.toCharArray()){
			if(c == '\n'){
				y += 7 * size;
			}
		}
		return y;
	}
}