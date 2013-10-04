package com.gmail.robmadeyou.Draw;

import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.World.Camera;
import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Layer;


import java.util.ArrayList;
import java.util.Arrays;

public class Collector {

    static ArrayList<DrawParameters> drawArraySorted = new ArrayList<DrawParameters>();
    
    public static ArrayList<Layer> drawArray = new ArrayList<Layer>();
    //TODO add arrays within arrays, where each array has a specified layer
    /*
     * This will save some performance as when rendering lots of objects at the same time the engine will not have
     * to sort hundreds of draw methods, but instead it will check if anything more was added and then render.
     * If nothing was added then leave the sorted array alone as it has been sorted properly already
     */
    static ArrayList<DrawParameters> drawArrayUnsorted = new ArrayList<DrawParameters>();

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
    	
    	for(Camera cam : Engine.cameraList){
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
            	for(Layer l : drawArray){
            		if(l.getNumber() == layer){
            			l.add(p);
            			return true;
            		}
            	}
            	drawArray.add(new Layer(layer));
            	Layer[] temp = new Layer[drawArray.size()];
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
        for(Layer l : drawArray){
        	l.clear();
        }
    }

    
    public static class DrawParameters {

        private float x, y, w, h;
        private int texID;
        private int layerID;
        private Color color;
        private String type;
        private boolean useTranslate;
        private float opacity;
        private boolean inverts;
        /**
         * 
         */
        public DrawParameters(String type, float x, float y, float width, float height) {
            this.type = type;
            this.x = x;
            this.y = y;
            this.w = width;
            this.h = height;
            this.texID = -1;
            this.color = Color.White;
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
         * @see Color class
         */
        public void setColor(Color color) {
        	this.color = color;
        }
        /**
         * Set the texture of the drawing object
         * <br>
         * This is an integer and will usually be created prior
         * to calling this method
         * @see TextureLoader
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
         * @see Camera
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
        }
        
        public String getType() {
        	return type;
        }

        public int getLayer() {
            return layerID;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        public float getWidth() {
            return w;
        }

        public float getHeight() {
            return h;
        }

        public Color getColor() {
            return color;
        }

        public int getTextureID() {
            return texID;
        }

        public boolean useTranslate() {
            return useTranslate;
        }

        public float getOpacity() {
            return opacity;
        }

        public boolean getInverts() {
            return inverts;
        }
    }
}
