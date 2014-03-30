package com.age.helper;

import com.age.exceptions.InvalidAsciiDigit;

/**
 * Created by apex on 27/03/14.
 */
public class Ascii {

    public static String stringToNumber(String text){
        String tmp = "";
        for(char c : text.toCharArray()){
            String ret = charToInt(c)+"";
            if(ret.length() < 3){
                ret = "0"+charToInt(c) +"";
            }else{
                ret = charToInt(c)+"";
            }
            tmp += ret;
        }
        return tmp;
    }

    public static String numberToString(String text){
        String tmp = "";
        text = text.replace(" ", "");

        if(text.length() % 3 != 0){
            text = "0" + text;
            try{
                if(text.length() % 3 != 0) throw new InvalidAsciiDigit();
            }catch (InvalidAsciiDigit e){
                e.printStackTrace();
            }
        }

        for(int i = 0; i < text.length(); i+=3){
            char[] chars = text.toCharArray();
            tmp += intToChar(Integer.parseInt(StringHelp.joinStringFromChar(new char[]{chars[i], chars[i+1], chars[i+2]})));
        }

        return tmp;
    }

    public static String numberToString(int text){
        return numberToString(text + "");
    }

    public static int charToInt(char c){
        return c;
    }

    public static char intToChar(int number) throws ArrayIndexOutOfBoundsException{
        return (char)number;
    }
}
