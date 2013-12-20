package com.age.graphics.render.shapes;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2d;

import com.age.graphics.Drawable;
import com.age.graphics.effects.TextureLoader;

public class Box extends Shape{

	public Box(double drawX, double drawY, double drawWidth, double drawHeight) {
		super(drawX, drawY, drawWidth, drawHeight);
	}

	public void draw() {

		int TextureID = getTexture();
		boolean inverts = isInverts();
		double x = getDrawX();
		double y = getDrawY();
		double width = getDrawWidth();
		double height = getDrawHeight();
		//TODO stop constantly binding textures, that's performance hungry
		getColor().bind();
		if (TextureID != -1) {
			TextureLoader.TextureInfo.get(TextureID).getTexture().bind();
			float xPercent = (float) TextureLoader.TextureInfo.get(TextureID).getXPercent();
			float yPercent = (float) TextureLoader.TextureInfo.get(TextureID).getYPercent();
			float widthPercent = (float) TextureLoader.TextureInfo.get(TextureID).getWidthPercent();
			float heightPercent = (float) TextureLoader.TextureInfo.get(TextureID).getHeightPercent();
			if (inverts) {
				glBegin(GL_QUADS);
					glTexCoord2d(xPercent + widthPercent, yPercent);
					glVertex2d(-(width / 2), -(height / 2));
					glTexCoord2d(xPercent, yPercent);
					glVertex2d(width / 2, -(height / 2));
					glTexCoord2d(xPercent, yPercent - heightPercent);
					glVertex2d(width / 2, height / 2);
					glTexCoord2d(xPercent + widthPercent, yPercent - heightPercent);
					glVertex2d(-(width / 2), height / 2);
				glEnd();
			} else {
				glBegin(GL_QUADS);
					glTexCoord2f(xPercent, yPercent);
					glVertex2d(-(width / 2), -(height / 2));
					glTexCoord2f(xPercent + widthPercent, yPercent);
					glVertex2d(width / 2, -(height / 2));
					glTexCoord2f(xPercent + widthPercent, yPercent - heightPercent);
					glVertex2d(width / 2, height / 2);
					glTexCoord2f(xPercent, yPercent - heightPercent);
					glVertex2d(-(width / 2), height / 2);
				glEnd();
			}
		}else{
			/*glBegin(GL_QUADS);
				glVertex2d(x, y);
				glVertex2d(x + width, y);
				glVertex2d(x + width, y + height);
				glVertex2d(x, y + height);
			glEnd();*/
			
			glBegin(GL_QUADS);
				glVertex2d(-(width / 2), -(height / 2));
				glVertex2d(width / 2, -(height / 2));
				glVertex2d(width / 2 , height / 2);
				glVertex2d(-(width / 2), height / 2);
			glEnd();
		}
	}
}
