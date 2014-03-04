package com.age.graphics.render;

import com.age.Age;
import com.age.graphics.Camera;
import com.age.graphics.Drawable;
import com.age.graphics.render.shapes.Line;

import static org.lwjgl.opengl.GL11.*;

public class Render {
	public static void all(Camera cam){
		for(int i = 0; i < Collector.drawArray.size(); i++){
			for(Drawable d : Collector.drawArray.get(i).getList()){
				if(d.isUseTranslate()){
				    glPushMatrix();
					glTranslatef(cam.getTranslateX(), cam.getTranslateY(), 0);
				}
				glPushMatrix();
					glScalef(Age.ratioX(), Age.ratioY(), 0);
					//glRotatef((float)d.getRotation(),(float) (d.getDrawY() ), (float) (d.getDrawX()), 0f);
					glPushMatrix();
                        glPushMatrix();
                        glTranslated(d.getRotationX(), d.getRotationY(), 0);
                        glRotatef((float) d.getRotation(), 0f, 0f, 1f);
                        if(!(d instanceof Line)) {
                            glTranslatef((float) ((d.getFinalDrawX() + d.getFinalDrawWidth() / 2) + d.getXOffset()), (float) ((d.getFinalDrawY() + d.getFinalDrawHeight() / 2) + d.getYOffset()), 0);
                        } 
                        glScalef(d.getScaleX(), d.getScaleY(), 0);
                        d.draw();
                        glPopMatrix();
                    glPopMatrix();
				glPopMatrix();
				if(d.isUseTranslate()){
				    glPopMatrix();
				}
			}
		}
	}
}
