package com.age.tests;

import com.age.Game;
import com.age.Screen;
import com.age.View;
import com.age.graphics.render.shapes.Box;

import java.util.Random;

public class BubbleSort extends View{

    static Box[] toSort = new Box[100];
    static int index = 0;
    static int times = 0;
    public BubbleSort(){
        super("BubbleSort");
    }

    @Override
    public void update() {
        toSort = bubbleSort(toSort);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void init() {
        Random ran = new Random();
        for(int i = 0; i < toSort.length; i++){
            int rando = ran.nextInt(Screen.getHeight());
            System.out.println(rando);
            toSort[i] = (Box) new Box(6 * i, 0, 6, rando).toEngine();
        }
    }

    public static void main(String... args){
        Game g = new Game("BubbleSort", 600,400);
        g.start(new BubbleSort());
    }

    public static Box[] bubbleSort(Box[] toSort){
        if(index != toSort.length -1 - times){
            if(toSort[index].getDrawHeight() > toSort[1 + index].getDrawHeight()){
                double temp = toSort[index].getDrawHeight();
                toSort[index].setDrawHeight(toSort[index+1].getDrawHeight());
                toSort[index + 1].setDrawHeight(temp);
            }
            index++;
        }else{
            index = 0;
            times++;
        }
        return toSort;
    }
}
