package com.age.graphics.ui;

import com.age.helper.Child;
import com.age.helper.Parent;

/**
 * Created by apex on 06/03/14.
 */
public class Image extends Gui implements Child{

    Parent parent;
    public Image(double x, double y, double width, double height){
        super(x,y,width,height);
    }

    @Override
    public void setParent(Parent p){
        this.parent = p;
    }

    @Override
    public Parent getParent(){
        return parent;
    }

    @Override
    public Image getOrigin(){
        return this;
    }

}
