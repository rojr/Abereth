package com.age.tests;

import com.age.Game;
import com.age.Screen;
import com.age.View;
import com.age.graphics.effects.Color;
import com.age.graphics.ui.Text;
import com.age.helper.Binary;
import org.lwjgl.Sys;

import java.util.ArrayList;

/**
 * Created by apex on 10/03/14.
 */
public class TheMatrixTest extends View{

    Text[] letters;
    Text t;
    public TheMatrixTest(){
        super("TheMatrixTest");
    }

    @Override
    public void dispose() {

    }

    @Override
    public void update() {
        String a =Text.GREEN + "";
        for(int j = 0; j < 100; j++){
            if(Math.random() >= 0.5){
                a += "0";
            }else{
                a += "1";
            }
        }
        a+= "\n";
        letters[0].set(a);

        for(int i = letters.length-2; i >= 0; i--){
            letters[i + 1].set(letters[i].getText());
        }
        System.out.println(Binary.fromBinaryToInt(a));
        t.setRotation(t.getRotation() + 1);
    }

    @Override
    public void init() {
        t = (Text) new Text("",20,20,0,0).toEngine();
        t.setColor(Color.RED);

        t.setRotationY(Screen.getWidth() / 2);
        t.setRotationX(Screen.getHeight() / 2);


        letters = new Text[40];

        for(int i = 0; i < letters.length; i++){
            Text te = (Text) new Text("", 0, 16 * i).toEngine();
            te.setSize(8);
            te.setRotationX(Screen.getWidth() / 2);
            te.setRotationY(Screen.getHeight() / 2);
            letters[i] =te;
        }
        long start = Sys.getTime();
        int speed = 0;
    }

    public static void main(String... args){
        Game g = new Game("TheMatrixTest - Matrix", 500,500);
        g.start(new TheMatrixTest());
    }
}
