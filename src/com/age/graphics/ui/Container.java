package com.age.graphics.ui;

import com.age.Age;
import com.age.graphics.Drawable;
import com.age.helper.Child;
import com.age.helper.Parent;

import java.util.ArrayList;

/**
 * Created by apex on 06/03/14.
 */
public class Container extends Gui implements Parent{


    private ArrayList<Child> children = new ArrayList<Child>();

    public Container(double x, double y){
        this(x,y,0,0);
    }

    public Container(double x, double y, double width, double height){
        super(x,y,width,height);
    }

    @Override
    public void setDrawX(double x){
        double prevX = getDrawX();
        double diff = x - prevX;
        super.setDrawX(x);
        for(Child c : children){
            c.getOrigin().setDrawX(c.getOrigin().getDrawX() + diff);
        }
    }

    @Override
    public void setDrawY(double y){
        double prevY = getDrawY();
        double diff = y - prevY;
        super.setDrawY(y);
        for(Child c : children){
            c.getOrigin().setDrawY(c.getOrigin().getDrawY() + diff);
        }
    }


    public ArrayList<Child> getChildren(){
        return children;
    }

    public void add(Child c){
        c.setParent(this);
        c.getOrigin().setRotationX(getRotationX());
        c.getOrigin().setRotationY(getRotationY());
        if(c.getOrigin().getDrawX() < getDrawX()){
            c.getOrigin().setDrawX(getDrawX());
        }
        if(c.getOrigin().getDrawY() < getDrawY()){
            c.getOrigin().setDrawY(getDrawY());
        }
        children.add(c);
    }

    public Drawable getOrigin(){
        return this;
    }

    @Override
    public void render(){
        super.render();
        for(Child c : children){
            c.getOrigin().render();
        }
    }

    @Override
    public void setRotation(int rotation){
        super.setRotation(rotation);
        for(Child c : children){
            c.getOrigin().setRotation(getRotation());
        }
    }

    @Override
    public void setRotationX(double x){
        super.setRotationX(x);
        for(Child c : children){
            c.getOrigin().setRotationX(x);
        }
    }

    @Override
    public void setRotationY(double y){
        super.setRotationY(y);
        for(Child c : children){
            c.getOrigin().setRotationY(y);
        }
    }

    @Override
    public Container toEngine(){
        return (Container) Age.add(this);
    }
}
