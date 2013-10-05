package com.gmail.robmadeyou.Draw;


import com.gmail.robmadeyou.Effects.TextureLoader;
import com.gmail.robmadeyou.Effects.Textures;
import com.gmail.robmadeyou.Input.Keyboard;
import com.gmail.robmadeyou.Input.Keyboard.Key;

import static org.lwjgl.opengl.GL11.*;

public class Box {

    public static void drawBox(double x, double y, double width, double height) {
        Textures.none.bind();
        glBegin(GL_QUADS);
        glTexCoord2d(1, 0);
        glVertex2d(x, y);
        glTexCoord2d(0, 0);
        glVertex2d(x + width, y);
        glTexCoord2d(0, 1);
        glVertex2d(x + width, y + height);
        glTexCoord2d(1, 1);
        glVertex2d(x, y + height);
        glEnd();
    }

    public static void drawBox(float x, float y, float width, float height, int TextureID, boolean inverts) {

        TextureLoader.TextureInfo.get(TextureID).getTexture().bind();
        float xPercent = (float) TextureLoader.TextureInfo.get(TextureID).getXPercent();
        float yPercent = (float) TextureLoader.TextureInfo.get(TextureID).getYPercent();
        float widthPercent = (float) TextureLoader.TextureInfo.get(TextureID).getWidthPercent();
        float heightPercent = (float) TextureLoader.TextureInfo.get(TextureID).getHeightPercent();
        if (inverts) {
            glBegin(GL_QUADS);
            glTexCoord2d(xPercent + widthPercent, yPercent);
            glVertex2d(x, y);
            glTexCoord2d(xPercent, yPercent);
            glVertex2d(x + width, y);
            glTexCoord2d(xPercent, yPercent - heightPercent);
            glVertex2d(x + width, y + height);
            glTexCoord2d(xPercent + widthPercent, yPercent - heightPercent);
            glVertex2d(x, y + height);
            glEnd();
        } else {
            glBegin(GL_QUADS);
            glTexCoord2f(xPercent, yPercent);
            glVertex2f(x, y);
            glTexCoord2f(xPercent + widthPercent, yPercent);
            glVertex2f(x + width, y);
            glTexCoord2f(xPercent + widthPercent, yPercent - heightPercent);
            glVertex2f(x + width, y + height);
            glTexCoord2f(xPercent, yPercent - heightPercent);
            glVertex2f(x, y + height);
            glEnd();
        }
    }
}
