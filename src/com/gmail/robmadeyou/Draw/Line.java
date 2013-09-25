package com.gmail.robmadeyou.Draw;

import static org.lwjgl.opengl.GL11.*;

public class Line {
    /**
     * Simply draws lines, this is used by the rendering system to draw single lines.
     * 
     * !!It is advised against to use this as well as the rendering system as independant calls would
     * most likely not draw lines (unless called after the Screen.refresh() method!!
     */
    public static void drawLine(float x, float y, float x2, float y2) {
        glBegin(GL_LINES);
        glVertex2f(x, y);
        glVertex2f(x2, y2);
        glEnd();
    }
}
