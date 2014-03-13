package com.age.graphics.effects;

import com.age.Age;
import com.age.Screen;
import com.age.graphics.Drawable;
import com.age.graphics.ui.Container;
import com.age.graphics.ui.Gui;
import com.age.helper.Child;
import com.age.helper.Parent;

/**
 * Created by apex on 11/03/14.
 */
public class Emitter extends Container {

    private boolean continues;
    private int maxDots;
    private double dotWidth, dotHeight;
    private double speed;
    public Emitter(double x, double y, double width, double height){
        super(x,y,width,height);
        continues = true;
        maxDots = continues ? 0 : 10;
        this.dotHeight = 5;
        this.dotWidth = 5;
        this.speed = 1;
    }

    public Emitter(double x, double y, double dotWidth, double dotHeight, double speed){
        super(x,y,0,0);
        this.continues = true;
        this.maxDots = continues ? 0 : 10;
        this.dotHeight = dotHeight;
        this.dotWidth = dotWidth;
        this.speed = speed;
        setOpacity(1f);
    }

    public void setContinues(boolean args){
        this.continues = args;
    }

    public boolean isContinues(){
        return continues;
    }

    public void setMaxDots(int ammount){
        this.maxDots = ammount;
    }

    public int getMaxDots(){
        return maxDots;
    }

    @Override
    public void updateChildren(){
        if(!continues){
            if(maxDots > 0){
                add(new Dot(this,getDrawX(),getDrawY(),dotWidth, dotHeight, speed));
                maxDots--;
            }
        }else{
            add(new Dot(this,getDrawX(),getDrawY(), dotWidth, dotHeight, speed));
        }

        for(int i = 0; i < getChildren().size(); i++){
            getChildren().get(i).getOrigin().render();
            if(getChildren().get(i).getOrigin().getOpacity() <= 0.002) getChildren().remove(i);
        }
    }

    private class Dot extends Gui implements Child{
        private Parent parent;
        double dX, dY;
        float opacityRate;
        public Dot(Parent parent, double x, double y, double width, double height, double speed){
            super(x,y,width,height);
            this.parent = parent;
            this.dX = (0.5 - Math.random()) * speed;
            this.dY = (0.5 - Math.random()) * speed;
            setOpacity(1f);
            setTexture(Age.letterTexID.get((int) (Math.random() * Age.letterTexID.size() - 1)));
        }

        @Override
        public Parent getParent(){
            return parent;
        }

        @Override
        public Drawable getOrigin(){
            return this;
        }


        @Override
        public void render(){
            super.render();
            setDrawX(getDrawX() + dX * Screen.delta);
            setDrawY(getDrawY() + dY * Screen.delta);
            setOpacity(getOpacity() * 0.99f);
        }

        @Override
        public void setParent(Parent parent) {
            this.parent = parent;
        }
    }

}
