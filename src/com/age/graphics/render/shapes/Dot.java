package com.age.graphics.render.shapes;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2d;
import static org.lwjgl.opengl.GL11.GL_POINTS;
import static org.lwjgl.opengl.GL11.glPointSize;

/**
 * Created by apex on 04/03/14.
 */
public class Dot extends Shape{
    public Dot(double x, double y){
        this(x,y,1);
    }

    public Dot(double x, double y, int size){
        super(x,y,size,size);
    }
    @Override
    public void draw(){
        glPointSize((float)getDrawWidth());
        glBegin(GL_POINTS);
            glVertex2d(getDrawX(),getDrawY());
        glEnd();
    }

    @Override
    public Dot toEngine(){
        return (Dot)super.toEngine();
    }
}
