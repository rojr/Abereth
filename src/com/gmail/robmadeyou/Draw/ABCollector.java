package com.gmail.robmadeyou.draw;

import com.gmail.robmadeyou.effects.ABColor;
import com.gmail.robmadeyou.world.ABCamera;
import com.gmail.robmadeyou.world.ABWorld;
import com.gmail.robmadeyou.Abereth;
import com.gmail.robmadeyou.ABLayer;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Class housing all of the draw statements that have been called within the engine, then are rendered at the end of the frame.
 * <br>
 * Here all the sorting happens to make sure all the objects go into the right places and when rendered are in the right order
 * hence the layering system
 * @see <a href="http://google.com">Link to documentation </a>
 * @author Apex
 *
 */
public class ABCollector {
	/**
	 *  ArrayList that houses all of the layer that the drawn objects are held in.
	 */
    public static ArrayList<ABLayer> drawArray = new ArrayList<ABLayer>();
    /**
     * 
     * Adds a call to the array that is left for sorting at the end of the tick
     * <br>
     * 
     * @return This returns a boolean to say if the adding was done right without errors
     * 
     **/ 
    public static boolean add(DrawParameters p) {
    	/*
    	 * This is to make sure the graphic is in bounds when added, otherwise if it's not it won't add it
    	 */
    	for(ABCamera cam : Abereth.cameraList){
    		float tX = cam.getX();
        	float tY = cam.getY();
        	float cW = cam.getWidth();
        	float cH = cam.getHeight();
        
            float eX = p.getX();
            float eY = p.getY();
            float eW = p.getWidth();
            float eH = p.getHeight();
            boolean one = eX >= -tX && eX <= -tX + cW &&
                    eY >= -tY && eY <= -tY + cH;
            boolean two = eX + eW >= -tX && eX + eW <= -tX + cW &&
                    eY >= -tY && eY <= -tY + cH;
            boolean three = eX + eW >= -tX && eX + eW <= -tX + cW &&
                    eY + eH >= -tY && eY + eH <= -tY + cH;
            boolean four = eX >= -tX && eX <= -tX + cW &&
                    eY + eH >= -tY && eY + eH <= -tY + cH;
            if (one || two || three || four || !p.useTranslate) {
            	int layer = p.getLayer();
            	for(ABLayer l : drawArray){
            		if(l.getNumber() == layer){
            			l.add(p);
            			return true;
            		}
            	}
            	drawArray.add(new ABLayer(layer));
            	ABLayer[] temp = new ABLayer[drawArray.size()];
            	for(int i = 0; i < drawArray.size(); i++){
        			temp[i] = drawArray.get(i);
        		}
            	Arrays.sort(temp);
            	for(int i = 0; i < drawArray.size(); i++){
        			drawArray.set(i, temp[i]);
        		}
            	return true;
            	//drawArrayUnsorted.add(p)
            }
        }
    	return false;
    }

    /**
     * Clears both array lists that hold all the draw calls
     */
    public static void clear() {
        for(ABLayer l : drawArray){
        	l.clear();
        }
    }

    
    public static class DrawParameters {

        private float x, y, w, h;
        private int texID;
        private int layerID;
        private ABColor color;
        private String type;
        private boolean useTranslate;
        private float opacity;
        private boolean inverts;
        /**
         * New draw object that gets the data from instances of ABDrawable class
         * <br>
         * <b>Example:</b>new DrawParameters("Box, this");
         */
        public DrawParameters(String type, ABDrawable d){
        	this.type = type;
            this.x = d.getDrawX();
            this.y = d.getDrawY();
            this.w = d.getDrawWidth();
            this.h = d.getDrawHeight();
            this.texID = d.getTexture();
            this.color = d.getColor();
            this.layerID = d.getLayer();
            this.useTranslate = d.getUseTranslate();
            this.opacity = d.getOpacity();
            this.inverts = d.getInverts();
        }
        /**
         * 
         * New Draw object that has to be manually created
         * <br>
         * <b>Example:</b>new DrawParameters("box", 40, 40, 40, 40);
         * @param type
         * @param x
         * @param y
         * @param width
         * @param height
         */
        public DrawParameters(String type, float x, float y, float width, float height) {
            this.type = type;
            this.x = x;
            this.y = y;
            this.w = width;
            this.h = height;
            this.texID = -1;
            this.color = ABColor.White;
            this.layerID = 1;
            this.useTranslate = false;
            this.opacity = 1f;
            this.inverts = false;
        }
        
        /**
         * Set the type of drawing
         * <br>
         * This is a string and can be "box", or "line"
         * @see Draw package
         */
        public void setType(String type) {
        	this.type = type;
        }
        /**
         * Sets the layer of the drawing object
         * <br>
         * The higher the number the more in front it will be
         * rendered. So 4 would be rendered over 2
         * 
         * @see Layers and Render
         */
        public void setLayer(int layer) {
        	this.layerID = layer;
        }
        /**
         * Set the colour of the drawing object
         * <br>
         * This should be white if using textures, unless you want
         * it to be coloured
         * @see ABColor class
         */
        public void setColor(ABColor color) {
        	this.color = color;
        }
        /**
         * Set the texture of the drawing object
         * <br>
         * This is an integer and will usually be created prior
         * to calling this method
         * @see ABTextureLoader
         */
        public void setTextureID(int textureID) {
        	this.texID = textureID;
        }
        /**
         * Sets the boolean useTranslate
         * <br>
         * This determines whether the drawing object will
         * be fixed to the position on the screen or will it move along
         * with the map (as long as the map is able to move too)
         * 
         * @see ABCamera
         */
        public void setUseTranslate(boolean args) {
        	this.useTranslate = args;
        }
        /**
         * Sets the opacity of the drawing object
         * <br>
         * The lower the value is the more see through the object will be
         * 
         */
        public void setOpacity(float opacity) {
        	this.opacity = opacity;
        }
        /**
         * Sets the boolean inverts
         * <br>
         * 
         * If this is set to true then the texture will be inverted. Commonly used in animation, if the player moves to the opposite side
         * instead of having to draw two different textures the player is simply inverted and the animation follows as usual
         * 
         */
        public void setInverts(boolean args) {
        	this.inverts = args;
        }
        /**
         * Gets the type of object (box, line etc)
         * @return String
         */
        public String getType() {
        	return type;
        }
        /**
         * Gets the layer that the object is currently drawn on. <br> Default is usually 1
         * @return int
         */
        public int getLayer() {
            return layerID;
        }
        /**
         * Gets the X location of the drawn object
         * @return float
         */
        public float getX() {
            return x;
        }
        /**
         * Gets the Y location of the drawn object
         * @return float
         */
        public float getY() {
            return y;
        }
        /**
         * Gets the width of the drawn object
         * @return float
         */
        public float getWidth() {
            return w;
        }
        /**
         * Gets the height of the drawn object
         * @return float
         */
        public float getHeight() {
            return h;
        }
        /**
         * Gets the ABColor of the object
         * @return ABColor
         */
        public ABColor getColor() {
            return color;
        }
        /**
         * Get the texture ID of the object
         * @return int
         */
        public int getTextureID() {
            return texID;
        }
        /**
         * Get if the object uses translate or not. Translate is used for camera manipulation. If this value is set to false then the coordinates will
         * correspond with the screen, if set to true the coordinates will correspond to the map and in turn let the camera move out of the screen for the character
         * @see Documentation on useTranslate for more information
         * @return boolean
         */
        public boolean useTranslate() {
            return useTranslate;
        }
        /**
         * Returns a value between 0 and 1 regarding the opacity of the drawn object
         * @return float
         */
        public float getOpacity() {
            return opacity;
        }
        /**
         * Returns a boolean value of whether the drawn object inverts or not (usually used for textures and movement to avoid having to make multiple textures 
         * @return boolean
         */
        public boolean getInverts() {
            return inverts;
        }
    }
}
