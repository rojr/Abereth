package com.gmail.robmadeyou.Entity;

import com.gmail.robmadeyou.Block.Block;
import org.lwjgl.util.vector.Vector2f;

public class Entity {

    protected Vector2f location, dimensions, direction, origLoc, origDim, origDir;
    private double x, y, dX, dY, speed, originalSpeed = 1,
            originalX, originalY, originalDX, originalDY;
    private int number, h, w, originalW, originalH, layer;
    private boolean wasUpdated = false;;

    public Entity() {
        location = new Vector2f();
        dimensions = new Vector2f();
        direction = new Vector2f();
        origLoc = new Vector2f();
        origDim = new Vector2f();
        origDir = new Vector2f();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int num) {
        this.number = num;
    }

    public void setWidth(int w) {
        dimensions.x = w;
    }

    public void setHeight(int h) {
        dimensions.y = h;
    }

    public void setX(double x) {
        location.x = (float) x;
    }

    public void setY(double y) {
        location.y = (float) y;
    }

    public void setDX(double dX) {
        direction.x = (float) dX;
    }

    public void setDY(double dY) {
        direction.y = (float) dY;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getX() {
        return location.x;
    }

    public double getY() {
        return location.y;
    }

    public double getDX() {
        return dimensions.x;
    }

    public double getDY() {
        return dimensions.y;
    }

    public double getSpeed() {
        return speed;
    }

    public int getWidth() {
        return (int) dimensions.x;
    }

    public int getHeight() {
        return (int) dimensions.y;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public void setOriginalWidth(int w) {
        origDim.x = w;
    }

    public void setOriginalHeight(int h) {
        origDim.y = h;
    }

    public void setOriginalX(double x) {
        origLoc.x = (float) x;
    }

    public void setOriginalY(double y) {
        origLoc.y = (float) y;
    }

    public void setOriginalDX(double dX) {
        origDir.x = (float) dX;
    }

    public void setOriginalDY(double dY) {
        origDir.y = (float) dY;
    }

    public void setOriginalSpeed(double speed) {
        this.originalSpeed = speed;
    }
    
    public void setWasUpdated(boolean updated){
    	this.wasUpdated = updated;
    }

    public double getOriginalX() {
        return origLoc.x;
    }

    public double getOriginalY() {
        return origLoc.y;
    }

    public double getOriginalDX() {
        return origDir.x;
    }

    public double getOriginalDY() {
        return origLoc.y;
    }

    public double getOriginalSpeed() {
        return originalSpeed;
    }

    public int getOriginalWidth() {
        return (int) origDim.x;
    }

    public int getOriginalHeight() {
        return (int) origDim.y;
    }
    
    public boolean getWasUpdated(){
    	return wasUpdated;
    }

    public int getLayer() {
        return layer;
    }

    public Entity getType() {
        return this;
    }
    public boolean intersects(Entity other){
    	return false;
    }
    public boolean isNear(Entity other) {
    	if (other != null) {
            int oX = (int) other.getX() + getWidth() / 2;
            int oY = (int) other.getY() + getHeight() / 2;

            boolean one = oX >= getX()
                    && oX <= getX() + getWidth()
                    && oY >= getY() && oY <= getY() + getHeight();
            boolean two = oX >= getX() - getWidth()
                    && oX <= getX() + getWidth() * 2
                    && oY >= getY() - getHeight()
                    && oY <= getY() + getHeight() * 2;

            if (one || two) {
                return true;
            }
        }
        return false;
    }

    public void doEffectFromBlock(Block type) {
    }

    public void removeEffectFromBlock(Block type) {
    }

    public void onUpdate(int delta) {
    }

    public void draw() {
    }
}
