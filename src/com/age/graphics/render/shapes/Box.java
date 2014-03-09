package com.age.graphics.render.shapes;

import com.age.graphics.effects.TextureLoader;

import static org.lwjgl.opengl.GL11.*;

public class Box extends Shape{

	public Box(double drawX, double drawY, double drawWidth, double drawHeight) {
		super(drawX, drawY, drawWidth, drawHeight);
	}

	public void draw() {
		int TextureID = getTexture();
		boolean invertsX = isInvertsX();
        boolean invertsY = isinvertsY();
		double width = getFinalDrawWidth();
		double height = getFinalDrawHeight();
		//TODO stop constantly binding textures, that's performance hungry
        glColor4f(getColor().getR(),getColor().getG(), getColor().getB(), getOpacity());
		if (TextureID != -1) {
			TextureLoader.TextureInfo.get(TextureID).getTexture().bind();
			float xPercent = (float) TextureLoader.TextureInfo.get(TextureID).getXPercent();
			float yPercent = (float) TextureLoader.TextureInfo.get(TextureID).getYPercent();
			float widthPercent = (float) TextureLoader.TextureInfo.get(TextureID).getWidthPercent();
			float heightPercent = (float) TextureLoader.TextureInfo.get(TextureID).getHeightPercent();
			if (invertsX || invertsY) {
                if(invertsX && invertsY){
				    glBegin(GL_QUADS);
                    System.out.println("aaa");
                        glTexCoord2d(xPercent - widthPercent, yPercent);
                        glVertex2d(-(width / 2), -(height / 2));
                        glTexCoord2d(xPercent, yPercent);
                        glVertex2d(width / 2, -(height / 2));
                        glTexCoord2d(xPercent, yPercent + heightPercent);
                        glVertex2d(width / 2, height / 2);
                        glTexCoord2d(xPercent - widthPercent, yPercent + heightPercent);
                        glVertex2d(-(width / 2), height / 2);
                    glEnd();
                }else if(invertsX){
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
                }else if(invertsY){
                    glBegin(GL_QUADS);
                        glTexCoord2f(xPercent, yPercent);
                        glVertex2d(-(width / 2), -(height / 2));
                        glTexCoord2f(xPercent - widthPercent, yPercent);
                        glVertex2d(width / 2, -(height / 2));
                        glTexCoord2f(xPercent - widthPercent, yPercent + heightPercent);
                        glVertex2d(width / 2, height / 2);
                        glTexCoord2f(xPercent, yPercent + heightPercent);
                        glVertex2d(-(width / 2), height / 2);
                    glEnd();
                }
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
