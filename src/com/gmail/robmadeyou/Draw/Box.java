package com.gmail.robmadeyou.Draw;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2d;

import com.gmail.robmadeyou.Effects.TextureLoader;
import com.gmail.robmadeyou.Effects.Textures;

public class Box {
	
	public static void drawBox(double x, double y, double width, double height){
		Textures.none.bind();
		glBegin(GL_QUADS);
			glTexCoord2d(1,0);
			glVertex2d(x , y);
			glTexCoord2d(0,0);
			glVertex2d(x + width, y);
			glTexCoord2d(0,1);
			glVertex2d(x + width, y + height);
			glTexCoord2d(1,1);
			glVertex2d(x , y + height);
		glEnd();
	}
	public static void drawBox(double x, double y, double width, double height, int TextureID, boolean inverts){
		
		TextureLoader.TextureInfo.get(TextureID).getTexture().bind();
		double xPercent = TextureLoader.TextureInfo.get(TextureID).getXPercent();
		double yPercent = TextureLoader.TextureInfo.get(TextureID).getYPercent();
		double widthPercent = TextureLoader.TextureInfo.get(TextureID).getWidthPercent();
		double heightPercent = TextureLoader.TextureInfo.get(TextureID).getHeightPercent();
		
		if(inverts){
			glBegin(GL_QUADS);
				glTexCoord2d(xPercent + widthPercent, yPercent);
				glVertex2d(x , y);
				glTexCoord2d(xPercent, yPercent);
				glVertex2d(x + width, y);
				glTexCoord2d(xPercent, yPercent - heightPercent);
				glVertex2d(x + width, y + height);
				glTexCoord2d(xPercent + widthPercent, yPercent - heightPercent);
				glVertex2d(x , y + height);
			glEnd();
		}else{
			glBegin(GL_QUADS);
				glTexCoord2d(xPercent, yPercent);
				glVertex2d(x , y);
				glTexCoord2d(xPercent + widthPercent, yPercent);
				glVertex2d(x + width, y);
				glTexCoord2d(xPercent + widthPercent, yPercent - heightPercent);
				glVertex2d(x + width, y + height);
				glTexCoord2d(xPercent, yPercent - heightPercent);
				glVertex2d(x , y + height);
			glEnd();
		}
	}
}
