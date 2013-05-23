package com.gmail.robmadeyou.draw;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2f;

import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.TextureLoader;

public class Box {
	
	public static void drawBox(int x, int y, int width, int height, Color color){
		
	}
	public static void drawTexturedBox(int TextureID, int x, int y, int width, int height, Color color){
		
		TextureLoader.TextureInfo.get(TextureID).getTexture().bind();
		double xPercent = TextureLoader.TextureInfo.get(TextureID).getXPercent();
		double yPercent = TextureLoader.TextureInfo.get(TextureID).getYPercent();
		double widthPercent = TextureLoader.TextureInfo.get(TextureID).getWidthPercent();
		double heightPercent = TextureLoader.TextureInfo.get(TextureID).getHeightPercent();
		
		color.bind();
		glBegin(GL_QUADS);
			glTexCoord2d(xPercent, yPercent);
			glVertex2f(x , y);
			glTexCoord2d(xPercent + widthPercent, yPercent);
			glVertex2f(x + width, y);
			glTexCoord2d(xPercent + widthPercent, yPercent + heightPercent);
			glVertex2f(x + width, y + height);
			glTexCoord2d(xPercent, yPercent + heightPercent);
			glVertex2f(x , y + height);
		glEnd();
	}
}
