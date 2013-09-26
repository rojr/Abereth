package com.gmail.robmadeyou.Gui;

import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Draw.Collector.DrawParameters;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Input.Mouse;

/*
 * Buttons are quite simple, they really serve only one purpose, they can't
 * be moved about but can be made to useTranslate (so when the camera moves, so does the
 * button)
 * 
 * All Gui objects implement the GUI interface
 */
public class Button implements Gui {
    private double x, y;
    private int width, height, layer, texture;
    private float opacity;
    private boolean useTranslate;
    private boolean isPressed, isReleased, isOver;
    private Color color;
    private String state;

    public Button(String state, double x, double y, int width, int height, int layer) {
        this.state = state;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.layer = layer;
        this.texture = -1;
        this.opacity = 1f;
        this.useTranslate = false;
        this.color = Color.White;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public void setTexture(int texture) {
        this.texture = texture;
    }

    public void useTranslate(boolean args) {
        this.useTranslate = args;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public void setColor(Color color){
    	this.color = color;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLayer() {
        return layer;
    }

    public String getState() {
        return state;
    }
    
    public Color getColor(){
    	return color;
    }

    public boolean isMouseOver() {
        isOver = false;
        int mX = (int) Mouse.getTranslatedX();
        int mY = (int) Mouse.getTranslatedY();
        if (mX >= x && mX <= x + width && mY >= y && mY <= y + height) {
            isOver = true;
            return true;
        }
        return false;
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
    	DrawParameters p = new DrawParameters("box", x, y, width, height);
    	p.setTextureID(texture);
    	p.setColor(color);
    	p.setOpacity(opacity);
    	p.setLayer(layer);
    	p.setUseTranslate(useTranslate);
        Collector.add(p);
    }
}
