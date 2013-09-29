package com.gmail.robmadeyou.Gui;

import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Draw.Collector.DrawParameters;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Input.Mouse;


public class Button extends Gui {
    private boolean useTranslate;
    private boolean isPressed, isReleased, isOver;
    private String state;

    public Button(float x, float y) {
    	super(x,y);
        this.useTranslate = false;
    }

    public void useTranslate(boolean args) {
        this.useTranslate = args;
    }
    
    public String getState() {
        return state;
    }

    public boolean isPressed() {
        isPressed = false;
        if (isMouseOver()) {
            if (Mouse.leftMouseButtonDown) {
                isPressed = true;
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
        Collector.add(p);
    }
}
