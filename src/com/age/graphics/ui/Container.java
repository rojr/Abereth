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

    /**
     * Only changes the x location of the container itself and not the children.
     * <b>Only is changed if single is set to false</b>.
     * @param x
     * @param single if true changes all child objects. If false only changes parent
     */
    public void setDrawX(double x, boolean single){
        if(single){
            setDrawX(x);
        }else{
            super.setDrawX(x);
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

    /**
     * Only changes the y location of the container itself and not the children.
     * <b>Only is changed if single is set to false</b>.
     * @param y
     * @param single if true changes all child objects. If false only changes parent
     */
    public void setDrawY(double y, boolean single){
        if(single){
            setDrawY(y);
        }else{
            super.setDrawY(y);
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
        updateChildren();
    }

    public void updateChildren(){
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
