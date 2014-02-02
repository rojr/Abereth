package com.age.tests;

import com.age.Screen;
import com.age.graphics.render.shapes.Box;

import java.util.Random;

public class QuickSort {

    static Box[] toSort = new Box[100];
    static int index = 0;
    static int times = 0;
    public static void main(String... args){
        Screen.create(600,600, "QuickSort");
        Random ran = new Random();
        for(int i = 0; i < toSort.length; i++){
            int rando = ran.nextInt(Screen.getHeight());
            System.out.println(rando);
            toSort[i] = (Box) new Box(6 * i, 0, 6, rando).toEngine();
        }

        while(!Screen.isCloseRequested()){
            Screen.update();

            toSort = bubbleSort(toSort);

            Screen.refresh(60);
        }
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
