package com.age.helper;

/**
 * Created by apex on 27/03/14.
 */
public class Binary {
    public static String toBinary(int in){
        int current = in;

        String tmp = "";
        while(current >= 2){
            int toAdd = current % 2;
            tmp += toAdd;
            current = current / 2;
        }
        tmp += current;
        return StringHelp.backWards(tmp);
    }

    public static String toBinary(String in){
        int current = Integer.parseInt(in);

        String tmp = "";
        while(current >= 2){
            int toAdd = current % 2;
            tmp += toAdd;
            current = current / 2;
        }
        tmp += current;
        return StringHelp.backWards(tmp);
    }

    public static int fromBinaryToInt(String number){
        int out = 0;
        number = number.replace(" ", "");
        for(int i = 0; i < number.toCharArray().length; i++){
            if(number.toCharArray()[i] == '0' || number.toCharArray()[i] == '1')
                out = (out * 2) + Integer.parseInt(number.toCharArray()[i]+"");
        }
        return out;
    }
}
