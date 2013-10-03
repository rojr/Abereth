package com.gmail.robmadeyou.Draw;

import com.gmail.robmadeyou.Draw.Collector.DrawParameters;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.Textures;
import com.gmail.robmadeyou.Screen;

import static org.lwjgl.opengl.GL11.*;

public class Render {
    /**
     * This method renders everything that is in the sorted array in order of layers, this gives the effect of having things in front of other things and behind certain others
     */
    public static void renderAll(double startX, double startY, double camX, double camY, double camWidth, double camHeight) {
        for (int i = 0; i < Collector.drawArray.size(); i++) {
        	for(DrawParameters p : Collector.drawArray.get(i).getList()){
            //Initializes all the variables used for rendering afterwards
             
            double x = p.getX();
            double y = p.getY();
            double width = p.getWidth();
            double height = p.getHeight();
            int texID = p.getTextureID();
            String type = p.getType();
            Color color = p.getColor();
            Float opacity = p.getOpacity();
            boolean inverts = p.getInverts();
            boolean useTranslate = p.useTranslate();
            //A check is done if the objects are in bounds of the camera
            boolean one = x >= -camX && x <= -camX + camWidth &&
                    y >= -camY && y <= -camY + camHeight;
            boolean two = x + width >= -camX && x + width <= -camX + camWidth &&
                    y >= -camY && y <= -camY + camHeight;
            boolean three = x + width >= -camX && x + width <= -camX + camWidth &&
                    y + height >= -camY && y + height <= -camY + camHeight;
            boolean four = x >= -camX && x <= -camX + camWidth &&
                    y + height >= -camY && y + height <= -camY + camHeight;

            if (one || two || three || four || !useTranslate) {
                Textures.none.bind();
                color.bind(opacity);
                if (p.useTranslate()) {
                    glPushMatrix();
                    glTranslated(camX, camY, 0);
                }
                //Here we determine the type of object to draw, either line, box
                if (type.toLowerCase().equals("box")) {
                    if (texID != -1) {
                        Box.drawBox((float)(x + startX),(float) (y + startY),(float) width,(float) height, texID, inverts);
                    } else {
                        Box.drawBox(x + startX, y + startY, width, height);
                    }
                } else if (type.toLowerCase().equals("line")) {
                    Line.drawLine((float) x, (float) y, (float) width, (float) height);
                }
                if (p.useTranslate()) {
                    glPopMatrix();
                }
            }
        	}
        }
    }
}
