package com.age.graphics;

import com.age.Age;
import com.age.Screen;
import com.age.graphics.effects.Color;
import com.age.graphics.effects.TextureLoader;
import com.age.graphics.render.Collector;
import com.age.logic.input.Mouse;
import com.age.world.Tile;
import com.age.world.World;

public abstract class Drawable {
	private double drawX, drawY, drawWidth, drawHeight, xOffset, yOffset;
	private double finalX, finalY, finalW, finalH;
	/**
	 * Used if object is even slightly outside of the region it is supposed to be in
	 */
	private double bX, bY, bW, bH;
	private int layer, rotation, texture;

    private double rotationX, rotationY;

	private float scaleX, scaleY, opacity;
	private Color color;
	private boolean invertsX, invertsY, useTranslate, wasPressed,boundsSet, boundUseTranslate, isVisible;
	/**
	 * Boolean to check if this class has been added to the clicked array (Age)
	 */
	private boolean isAdded = false;

    private long id;

    /**
     * Default constructor
     */
    public Drawable(){
        this(0,0);
    }

    public Drawable(double x, double y){
        this(x,y,20,20);
    }

	public Drawable(double drawX, double drawY, double drawWidth, double drawHeight) {
		this.drawX = drawX;
		this.drawY = drawY;
		this.drawWidth = drawWidth;
		this.drawHeight = drawHeight;
        this.xOffset = 0;
        this.yOffset =0;
		this.layer = 1;
		this.rotation = 0;
		this.scaleX = 1;
		this.scaleY = 1;
		this.texture = -1;
		this.invertsX = false;
        this.invertsY = false;
		this.color = Color.WHITE;
		this.setUseTranslate(false);
		this.boundsSet = false;
		this.bX = -1;
		this.bY = -1;
		this.bW = -1;
		this.bH = -1;
		this.finalX = drawX;
		this.finalY = drawY;
		this.finalW = drawWidth;
		this.finalH = drawHeight;
		wasPressed = false;
		this.isVisible = true;
        this.opacity = 1f;
        this.id = System.nanoTime();
        //By default, it's set to the centre of the un-transformed drawable object
        //TODO make this change accordingly when the object moves.
        this.rotationX = drawX + drawWidth / 2;
        this.rotationY = drawY + drawHeight / 2;
    }

	public double getDrawX() {
		return drawX;
	}

	public double getDrawY() {
		return drawY;
	}
	public double getFinalDrawX(){
		return finalX;
	}
	public double getFinalDrawY(){
		return finalY;
	}
	public double getFinalDrawWidth(){
		return finalW;
	}
	public double getFinalDrawHeight(){
		return finalH;
	}

	public double getDrawWidth() {
		return drawWidth;
	}

	public double getDrawHeight() {
		return drawHeight;
	}
    public double getXOffset(){
        return xOffset;
    }

    public double getYOffset(){
        return yOffset;
    }
	public int getLayer() {
		return layer;
	}

	public int getRotation() {
		return rotation;
	}

	public float getScaleX() {
		return scaleX;
	}

	public float getScaleY() {
		return scaleY;
	}

	public int getTexture() {
		return texture;
	}

	public Color getColor() {
		return color;
	}
    public long getID(){return id;}
    public float getOpacity(){return opacity;}

    public double getRotationX(){
        return rotationX;
    }

    public double getRotationY(){
        return rotationY;
    }

	public boolean isUseTranslate() {
		return useTranslate;
	}
	
	public double getBoundX(){
		return bX;
	}
	public double getBoundY(){
		return bY;
	}
	public double getBoundWidth(){
		return bW;
	}
	public double getBoundHeight(){
		return bH;
	}
	public boolean isVisible(){
		return isVisible;
	}

	public boolean isClicked(){
		double x = drawX, y = drawY, w = drawWidth, h = drawHeight;
		int mX, mY;
		if(useTranslate){
			mX = Mouse.getTranslatedX();
			mY = Mouse.getTranslatedY();
		}else{
			mX = Mouse.getX();
			mY = Mouse.getY();
		}
		if(mX >= x && mX <= x + w && mY >= y && mY <= y + h && Mouse.isLeftMouseButtonDown()){
			wasPressed = true;
		}else{
			if(wasPressed){
				if(!Mouse.isLeftMouseButtonDown() || mX >= x && mX <= x + w && mY >= y && mY <= y + h){
					wasPressed = false;
					if(!isAdded){
						isAdded = true;
						Age.clickedList.add(this);
					}
					return true;
				}else{
					wasPressed = false;
				}
			}
		}
		return false;
	}
	
	public void setDrawX(double x) {
		this.drawX = x;
	}

	public void setDrawY(double y) {
		this.drawY = y;
	}

	public void setDrawWidth(double width) {
		this.drawWidth = width;
	}

	public void setDrawHeight(double height) {
		this.drawHeight = height;
	}
    public void setXOffset(double x){
        this.xOffset = x;
    }
    public void setYOffset(double y){
        this.yOffset = y;
    }

    /**
     * Sets the opacity of the drawable object.
     * @param f Can be anything from 0 to 1
     */
    public void setOpacity(Float f) {this.opacity = f;}

    /**
     * Strongly advise <b>DO NOT CALL THIS</b>, it's to set
     * the ID so the Engine knows what each object is. Avoids massive
     * brute force loops.
     * @param id
     */
    public void setID(int id){
        this.id = id;
    }
	public void setBoundsX(double x){
		this.boundsSet = true;
		this.bX = x;
	}
	
	public void setBoundsY(double y){
		this.boundsSet = true;
		this.bY = y;
	}
	
	public void setBoundsWidth(double w){
		this.boundsSet = true;
		this.bW = w;
	}
	public void setBoundsHeight(double h){
		this.boundsSet = true;
		this.bH = h;
	}
	
	public void setUseBounds(boolean args){
		this.boundsSet = args;
	}
	
	public void setUseBoundsTranslate(boolean args){
		this.boundUseTranslate = args;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}

	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}

	public boolean isInvertsX() {
		return invertsX;
	}
    public boolean isinvertsY() {
        return invertsY;
    }

	public void setTexture(int texture) {
		this.texture = texture;
	}

	public void setColor(Color color) {
		Color c = new Color(color.getR(), color.getG(), color.getB());

        //This creates a new color, instead of simply referencing Color.
        //Allows for color manipulation effects to not be saved within the color
        //as final objects can still be changed.
        this.color = c;
	}

	public void setInvertsX(boolean inverts) {
		this.invertsX = inverts;
	}
    public void setInvertsY(boolean inverts) {
        this.invertsY = inverts;
    }

    public void setInverts(boolean x, boolean y){
        this.invertsX = x;
        this.invertsY = y;
    }

	public void setUseTranslate(boolean useTranslate) {
		this.useTranslate = useTranslate;
	}
	public void setScale(float x, float y){
		this.scaleX = x;
		this.scaleY = y;
	}

    /**
     * The x coordinate that the object is going to be
     * rotated at
     */
    public void setRotationX(double x){
        this.rotationX = x;
    }

    /**
     * @param y The Y coordinate that the object is going to be
     *          rotated at.
     */
    public void setRotationY(double y){
        this.rotationY = y;
    }

	public void setLocationByCenter(int x, int y){
		this.drawX = x - drawWidth / 2;
		this.drawY = y - drawHeight / 2;
	}
	
	private void fixToFitBounds(boolean topLeft, boolean topRight, boolean botLeft, boolean botRight){
		//System.out.println(topLeft + "   " + topRight + "   " + botLeft + "   " + botRight);
		if(!topLeft && !botLeft){
			//TODO yep.. this should be done
		}
	}
	
	public void render(){
		if(isVisible){
			isClicked();
            //TODO This is for resizing the quad and textures to create the illusion of the texture being cut
			this.finalX = drawX;
			this.finalY = drawY;
			this.finalW = drawWidth;
			this.finalH = drawHeight;
			if(boundsSet){
				boolean topLeft,topRight,bottomLeft,bottomRight;
				if(boundUseTranslate){
					//"Prettify"!!!
					float tX = Age.cameraMain.getTranslateX();
					float tY = Age.cameraMain.getTranslateY();
					topLeft =     drawX - tX             >= bX && drawX - tX             <= bX + bW && drawY - tY              >= bY && drawY - tY              <= bY + bH;
					topRight =    drawX - tX + drawWidth >= bX && drawX - tX + drawWidth <= bX + bW && drawY - tY              >= bY && drawY - tY              <= bY + bH;
					bottomLeft =  drawX - tX             >= bX && drawX - tX             <= bX + bW && drawY - tY + drawHeight >= bY && drawY - tY + drawHeight <= bY + bH;
					bottomRight = drawX - tX + drawWidth >= bX && drawX - tX + drawWidth <= bX + bW && drawY - tY + drawHeight >= bY && drawY - tY + drawHeight <= bY + bH;
				}else{
					topLeft =     drawX             >= bX && drawX             <= bX + bW && drawY              >= bY && drawY              <= bY + bH;
					topRight =    drawX + drawWidth >= bX && drawX + drawWidth <= bX + bW && drawY              >= bY && drawY              <= bY + bH;
					bottomLeft =  drawX             >= bX && drawX             <= bX + bW && drawY + drawHeight >= bY && drawY + drawHeight <= bY + bH;
					bottomRight = drawX + drawWidth >= bX && drawX + drawWidth <= bX + bW && drawY + drawHeight >= bY && drawY + drawHeight <= bY + bH;
				}
				if(topLeft && topRight && bottomLeft && bottomRight){
				
				}else{
					fixToFitBounds(topLeft, topRight, bottomLeft, bottomRight);
				}
			}
			isAdded = false;
			if(Age.EmptyTexture != -1){
				TextureLoader.TextureInfo.get(Age.EmptyTexture).getTexture().bind();
			}
			Collector.add(this);
		}
	}

    public Drawable toEngine(){
        return Age.add(this);
    }

    public boolean remove(){
        return Age.remove(this);
    }

	public abstract void draw();
	
	public void onClick(){}
	public void onHover(){}
	
}
