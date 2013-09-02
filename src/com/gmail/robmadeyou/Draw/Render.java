package com.gmail.robmadeyou.Draw;

import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.Textures;
import com.gmail.robmadeyou.Screen;

import static org.lwjgl.opengl.GL11.*;

public class Render {
    public static void renderAll(double startX, double startY, double camX, double camY, double camWidth, double camHeight) {
        for (int i = 0; i < Collector.drawArraySorted.size(); i++) {
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
                if (Collector.drawArraySorted.get(i).useTranslate()) {
                    glPushMatrix();
                    glTranslated(camX, camY, 0);
                }
                if (type.toLowerCase().equals("box")) {
                    if (texID != -1) {
                        Box.drawBox(x + startX, y + startY, width, height, texID, inverts);
                    } else {
                        Box.drawBox(x + startX, y + startY, width, height);
                    }
                } else if (type.toLowerCase().equals("line")) {
                    Line.drawLine((float) x, (float) y, (float) width, (float) height);
                }
                if (Collector.drawArraySorted.get(i).useTranslate()) {
                    glPopMatrix();
                }
            }

            //TODO Add code for Lines, Points, so on so forth OK? :)
        }
    }
}
