package com.age.graphics.render;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import com.age.Screen;
import com.age.graphics.Camera;
import com.age.graphics.Drawable;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

public class Render {
	public static void all(Camera cam){
		for(int i = 0; i < Collector.drawArray.size(); i++){
			for(Drawable d : Collector.drawArray.get(i).getList()){
				float ratioX = Screen.getWidth() / Screen.originalDimensionX;
				float ratioY = Screen.getHeight() / Screen.originalDimensionY;
				glPushMatrix();
					glTranslatef(cam.getTranslateX(), cam.getTranslateY(), 0);
					glPushMatrix();
						glScalef(ratioX, ratioY, 0);
						//glRotatef((float)d.getRotation(),(float) (d.getDrawY() ), (float) (d.getDrawX()), 0f);
						glPushMatrix();
							glTranslatef((float) (d.getDrawX() + d.getDrawWidth() / 2),(float) (d.getDrawY() + d.getDrawHeight() / 2), 0);
							//glTranslatef((float) d.getDrawX(),(float) d.getDrawY(), 0);
							glRotatef((float)d.getRotation(),0f, 0, 1f);
							glPushMatrix();
								glScalef(d.getScaleX(), d.getScaleY(), 0);
								d.draw();
							glPopMatrix();
						glPopMatrix();
					glPopMatrix();
				glPopMatrix();
			
			}
		}
	}
}
