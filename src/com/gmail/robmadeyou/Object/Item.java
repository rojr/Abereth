package com.gmail.robmadeyou.Object;

import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Draw.Collector.DrawParameters;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Input.Mouse;
import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.World.World;

public class Item extends EObject{
    private int id;
    private boolean wasPressed = false;
    //private Container inGui = null;
    private boolean isVisible = false;


    //Possibly making some gravity effect
    //when the items are let go, they slow down
    //instead of just falling straight down

    //private int lastDX = 0, lastDY = 0;
    //private double leftDX = 0, leftDY = 0;

    public Item(float x, float y, float width, float height) {
    	super(x,y);
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
    }

    public boolean isVisible(){
    	return isVisible;
    }
    
    public void isVisible(boolean args){
    	this.isVisible = args;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPressed() {
        return isMouseOver() && Mouse.isLeftMouseButtonPressed();
    }

    /**
     * Boolean checking if the item is being held, only returned true if the mouse is
     * inside the item, this can be tricky to work with as the mouse would move too
     * fast from the item and the mouse would end up not being inside the bounds of the item
     * use isDragged better;
     */
    public boolean isHeld() {
        if (Mouse.leftMouseButtonDown && isMouseOver()) {
            wasPressed = true;
            return true;
        }
        return false;
    }

    public boolean isDragged() {
        isHeld();
        if (!Mouse.leftMouseButtonDown && wasPressed) {
            wasPressed = false;
            return false;
        }
        return wasPressed;
    }

    public void onUpdate() {
    	gravityEffect();
    }
    
    //public void setContainer(Container gui){
    //	this.inGui = gui;
    //}
    
    //public Container getContainer(){
    //	return inGui;
    //}
    
    public void gravityEffect(){
    	if (isDragged()) {
            setX(getX() + Mouse.getDX());
            setY(getY() + Mouse.getDY());
        } else {
            if (!World.isSolidAtLocation((int) Math.round(getX() / World.BLOCK_SIZE()), (int) Math.round(getY() / World.BLOCK_SIZE()))) {
                setY(getY() + (float)World.gravity(Screen.delta) * 10);
            }
        }
    }
    public void draw() {
    	DrawParameters p = new DrawParameters("box", getX(), getY(), getWidth(), getHeight());
    	p.setTextureID(getTexture());
    	p.setColor(Color.White);
    	p.setOpacity(1);
    	p.setLayer(getLayer());
    	p.setUseTranslate(true);
    
    	if(Collector.add(p)){
    		isVisible = true;
    	}else{
    		isVisible = false;
    	}
    }
}
