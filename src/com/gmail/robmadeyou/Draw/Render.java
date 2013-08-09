package com.gmail.robmadeyou.Draw;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslated;

import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.Textures;

public class Render {
	public static void renderAll(){
		for(int i = 0; i < Collector.drawArraySorted.size(); i++){
			double x = Collector.drawArraySorted.get(i).getX();
			double y = Collector.drawArraySorted.get(i).getY();
			double width = Collector.drawArraySorted.get(i).getWidth();
			double height = Collector.drawArraySorted.get(i).getHeight();
			int texID = Collector.drawArraySorted.get(i).getTextureID();
			String type = Collector.drawArraySorted.get(i).getType();
			Color color = Collector.drawArraySorted.get(i).getColor();
			Float opacity = Collector.drawArraySorted.get(i).getOpacity();
			boolean inverts = Collector.drawArraySorted.get(i).getInverts();
			boolean useTranslate = Collector.drawArraySorted.get(i).useTranslate();
			
			boolean one = x >= -Screen.translate_x && x <= -Screen.translate_x + Screen.getWidth() &&
					y >= -Screen.translate_y && y <= -Screen.translate_y + Screen.getHeight();
			boolean two = x + width >= -Screen.translate_x && x + width <= -Screen.translate_x + Screen.getWidth() &&
					y >= -Screen.translate_y && y <= -Screen.translate_y + Screen.getHeight();
			boolean three = x + width >= -Screen.translate_x && x + width <= -Screen.translate_x + Screen.getWidth() &&
					y + height >= -Screen.translate_y && y + height <= -Screen.translate_y + Screen.getHeight();
			boolean four = x >= -Screen.translate_x && x <= -Screen.translate_x + Screen.getWidth() &&
					y + height >= -Screen.translate_y && y + height<= -Screen.translate_y + Screen.getHeight();
			
			if(one || two || three || four || !useTranslate){
				Textures.none.bind();
				color.bind(opacity);
				if(Collector.drawArraySorted.get(i).useTranslate()){
					glPushMatrix();
					glTranslated(Screen.translate_x, Screen.translate_y, 0);
				}
				if(type.toLowerCase().equals("box")){
					if(texID != -1){
						Box.drawBox(x, y, width, height, texID, inverts);
					}else{
						Box.drawBox(x, y, width, height);
					}
				}
				if(Collector.drawArraySorted.get(i).useTranslate()){
					glPopMatrix();
				}
			}
			
			//TODO Add code for Lines, Points, so on so forth OK? :)
		}
	}
}
