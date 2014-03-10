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
        super(x,y);
        if(!(text.length() <= 0)){
            //TODO set up the array
        }
    }

    @Override
    public void add(Child c){
        if(c.getOrigin() instanceof Letter){
            getChildren().add(c);
        }else{
            try{
                System.out.println("aa");
                new InvalidObjectType("Letter").printStackTrace();
            }catch(Exception e){

            }
        }

    }
}