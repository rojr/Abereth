package com.age.helper;

import java.util.ArrayList;

/**
 * Created by apex on 13/03/14.
 */
public class Random {
    public static Integer choose(Integer[] list){
        return list[(int)(Math.random() * list.length)];
    }

    public static Integer choose(ArrayList<Integer> list){
        return list.get((int) (Math.random() * list.size()));
    }
}
