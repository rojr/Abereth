package com.gmail.robmadeyou.Entity;

import com.gmail.robmadeyou.Block.ABBlock;
import com.gmail.robmadeyou.Draw.ABDrawable;

import org.lwjgl.util.vector.Vector2f;

public abstract class ABEntity extends ABDrawable{

    protected Vector2f location, dimensions, direction, origLoc, origDim, origDir;
    private float speed;
    private float originalSpeed;
    private int number;
    private boolean wasUpdated = false;
    private boolean isOnScreen = false;
    private int health;
    private float speedDecrease;

    public ABEntity(float x, float y, float width, float height) {
    	super(new Vector2f(), new Vector2f());
    	location = new Vector2f();
    	dimensions = new Vector2f();
    	location.x = x;
    	location.y = y;
    	dimensions.x = width;
    	dimensions.y = height;
        direction = new Vector2f();
        origLoc = location;
        origDim = dimensions;
        origDir = new Vector2f();
        health = 1;
        setSpeed(1);
        speedDecrease = 0.8f;
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

    public void setSpeed(float speed) {
        this.speed = speed;
        this.speedDecrease = speed *= 0.8;
    }
    public void setSpeedDecrease(float speed){
    	this.speedDecrease = speed;
    }

    public float getX() {
        return location.x;
    }
    
    public boolean isOnScreen(){
    	return isOnScreen;
    }

    public float getY() {
        return location.y;
    }

    public double getDX() {
        return dimensions.x;
    }

    public double getDY() {
        return dimensions.y;
    }

    public float getSpeed() {
        return speed;
    }
    public float getSpeedDecrease(){
    	return speedDecrease;
    }
    
    public void isOnScreen(boolean args){
    	this.isOnScreen = args;
    }

    public float getWidth() {
        return dimensions.x;
    }

    public float getHeight() {
        return dimensions.y;
    }
    public int getHealth(){
    	return health;
    }

    public void setHealth(int health){
    	this.health = health;
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

    public void setOriginalSpeed(float speed) {
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

    public ABEntity getType() {
        return this;
    }
    public boolean intersects(ABEntity other){
    	return false;
    }
    public boolean isNear(ABEntity other) {
    	if (other != null) {
            float oX = other.getX() + getWidth() / 2;
            float oY = other.getY() + getHeight() / 2;

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

    public void doEffectFromBlock(ABBlock type) {
    }

    public void removeEffectFromBlock(ABBlock type) {
    }
    
    protected void backgroundRender(){
    	updateDrawValues(location, dimensions);
    }

    public abstract void update();
    
    public abstract void onUpdate(int delta);

    public abstract void draw();
}
