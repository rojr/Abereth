package com.age.graphics.ui;

import com.age.graphics.Drawable;
import com.age.helper.Child;
import com.age.helper.Parent;

/**
 * Created by apex on 10/03/14.
 */
public class Letter extends Gui implements Child{

    private Parent parent;
    public Letter(double x, double y, double width, double height){
        super(x,y,width,height);
    }

    public Parent getParent(){
        return parent;
    }

    public Drawable getOrigin(){
        return this;
    }

    public void setParent(Parent p){
        this.parent = p;
    }

}
