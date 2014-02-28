package com.age.graphics.render.shapes;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by apex on 28/02/14.
 */
public class Line extends Shape {

    public Line(double x1, double y1, double x2, double y2){
        super(x1,y1,x2,y2);
    }

    public void draw(){
        glColor4f(getColor().getR(), getColor().getG(), getColor().getB(), getOpacity());
        glBegin(GL_LINES);
            glVertex2d(getDrawX(), getDrawY());
            glVertex2d(getDrawWidth(), getDrawHeight());
        glEnd();
        System.out.println("a");
    }

    @Override
    public Line toEngine(){
        super.toEngine();
        return this;
    }
}
