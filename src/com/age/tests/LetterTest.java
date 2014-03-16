package com.age.tests;

import com.age.Game;
import com.age.Screen;
import com.age.View;
import com.age.graphics.effects.Color;
import com.age.graphics.ui.Image;
import com.age.graphics.ui.Letter;
import com.age.graphics.ui.Text;
import com.age.helper.Child;
import com.age.logic.input.Keyboard;
import org.lwjgl.Sys;

/**
 * Created by apex on 10/03/14.
 */
public class LetterTest extends View{

    Text[] letters;
    Text t;
    public LetterTest(){
        super("LetterTest");
    }

    @Override
    public void dispose() {

    }

    @Override
    public void update() {
        String a = Text.GREEN+"";
        for(int j = 0; j < 100; j++){
            if(Math.random() >= 0.5){
                a += "O";
            }else{
                a += "1";
            }
            double ran = 0.5;
            if(ran < 0.1){
                a += Text.BLUE;
            }else if(ran < 0.2){
                a += Text.GREEN;
            }else if(ran < 0.3){
                a += Text.RED;
            }
        }
        a+= "\n";
        letters[0].set(a);

        for(int i = letters.length-2; i >= 0; i--){
            letters[i + 1].set(letters[i].getText());
            //letters[i].setRotation(letters[i].getRotation()+(int)(Math.random() * 20));
        }
        System.out.println(Screen.actualFps);

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

        /*

        In this test press spacebar to see the text rotate at a random speed and then go back to the original location.
        String manipulation similar to Regular Expressions is also implemented here and will be tested.

         */

        long start = Sys.getTime();
        int speed = 0;
    }

    public static void main(String... args){
        Game g = new Game("LetterTest - Matrix", 500,500);
        g.start(new LetterTest());
    }
}
