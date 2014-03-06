package com.age.tests;

import com.age.Screen;
import com.age.graphics.effects.Color;
import com.age.graphics.ui.Container;
import com.age.graphics.ui.Image;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;

/**
 * Created by apex on 06/03/14.
 */
public class ContainerTest {
    public static void main(String... args){
        Screen.create(500,500,"");


        Container c = new Container(20,20,100,100).toEngine();
        Image i = new Image(40, 4, 200, 20);
        i.setColor(Color.Aqua);
        c.add(i);
        while(!Screen.isCloseRequested()){
            Screen.update();
            c.setRotation(c.getRotation()+1);

            glBegin(GL_TRIANGLES);
                glVertex2f(10,0);
                glVertex2f(0,20);
                glVertex2f(20,20);
            glEnd();

            Screen.refresh(60);
        }
    }
}
