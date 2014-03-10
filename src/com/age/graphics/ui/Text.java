package com.age.graphics.ui;

import com.age.exceptions.InvalidObjectType;
import com.age.helper.Child;


/**
 * Created by apex on 10/03/14.
 */
public class Text extends Container {


    public Text(double x, double y){
        this("", x, y);
    }
    public Text(String text, double x, double y){
        this(text, x, y, 0, 0);
    }

    public Text(String text, double x, double y, double width, double height){
        super(x,y,width,height);
        if((text.length() > 0)){
            //TODO set up the array
            for(int i = 0; i < text.toCharArray().length; i++){
                add(new Letter(text.toCharArray()[i], getDrawX() + i * 8, getDrawY(),8,8));
            }
        }
    }
      
    @Override
    public void add(Child c){
        if(c.getOrigin() instanceof Letter){
            super.add(c);
            System.out.println(((Letter) c.getOrigin()).getLetter());
        }else{
            try{
                System.out.println("aa");
                new InvalidObjectType("Letter").printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}