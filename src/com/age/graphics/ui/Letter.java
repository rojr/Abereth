package com.age.graphics.ui;

import com.age.Age;
import com.age.graphics.Drawable;
import com.age.helper.Child;
import com.age.helper.Parent;

/**
 * Created by apex on 10/03/14.
 */
public class Letter extends Gui implements Child{

    private Parent parent;
    private char c;
    public Letter(char c, double x, double y, double width, double height){
        super(x,y,width,height);
        this.c = c;
        setTexture(Age.getCharacterTexture(c));
    }

    public char getLetter(){
        return c;
    }

    public void setLetter(char c){
        this.c = c;
        setTexture(Age.getCharacterTexture(c));
    }

    public Parent getParent(){
        return parent;
    }

    public Letter getOrigin(){
        return this;
    }

    public void setParent(Parent p){
        this.parent = p;
    }

}
