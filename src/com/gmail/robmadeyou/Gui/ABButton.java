package com.gmail.robmadeyou.gui;

import com.gmail.robmadeyou.draw.ABCollector;
import com.gmail.robmadeyou.draw.ABCollector.DrawParameters;
import com.gmail.robmadeyou.peripherals.ABMouse;


public class ABButton extends ABGui {
    private String state;

    public ABButton(float x, float y) {
    	super(x,y);
    }

    public String getState() {
        return state;
    }

    public boolean isPressed() {
        if (isMouseOver()) {
            if (ABMouse.leftMouseButtonDown) {
                return true;
            }
        }
        return false;
    }

    private boolean lastIsPressed = false;

    public boolean isReleased() {
        if (lastIsPressed && !isPressed() && isMouseOver()) {
            lastIsPressed = isPressed();
            return true;
        }
        lastIsPressed = isPressed();
        return false;
    }

    public void onUpdate() {
        draw();
    }

    public void draw() {
    	
    	DrawParameters p = new DrawParameters("box", getX(), getY(), getWidth(), getHeight());
    		p.setTextureID(getTexture());
    		p.setColor(getColor());
    		p.setOpacity(getOpacity());
    		p.setLayer(getLayer());
    		p.setUseTranslate(getUseTranslate());
        if(ABCollector.add(p)){
        	System.out.println("Yay");
        }
    }
}
