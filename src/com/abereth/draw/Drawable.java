package com.abereth.draw;

import com.abereth.game.Draw;

public abstract class Drawable {

	private double drawX, drawY, drawWidth, drawHeight, xOffset, yOffset;
	private double finalX, finalY, finalW, finalH;
	/**
	 * Used if object is even slightly outside of the region it is supposed to be in
	 */
	private double bX, bY, bW, bH;

	private int layer, rotation, texture;

	private float scaleX, scaleY, opacity;
	private Color color;
	private boolean invertsX, invertsY, useTranslate, isVisible;
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
		this.bX = -1;
		this.bY = -1;
		this.bW = -1;
		this.bH = -1;
		this.finalX = drawX;
		this.finalY = drawY;
		this.finalW = drawWidth;
		this.finalH = drawHeight;
		this.isVisible = true;
		this.opacity = 1f;
		this.id = System.nanoTime();
		//By default, it's set to the centre of the un-transformed drawable object
		//TODO make this change accordingly when the object moves.
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
	public long getID(){
		return id;
	}
	public float getOpacity(){
		return opacity;
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
	public boolean isInvertsX() {
		return invertsX;
	}
	public boolean isinvertsY() {
		return invertsY;
	}


	public void setIsVisible(boolean visible) {this.isVisible = visible;}
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
	public void setOpacity(float f) {this.opacity = f;}
	/**
	 * Strongly advise <b>DO NOT CALL THIS</b>, it's to set
	 * the ID so the Engine knows what each object is. Avoids massive
	 * brute force loops.
	 * @param id
	 */
	public void setID(int id){
		this.id = id;
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
	public void setTexture(int texture) {
		this.texture = texture;
	}
	public void setColor( Color color ) {
		//If we are setting a lot of colors at once
		//this might break some stuff as creating new objects
		//can be heavy
		if( color != getColor() )
		{
			Color c = new Color(color.getR(), color.getG(), color.getB());

			//This creates a new color, instead of simply referencing Color.
			//Allows for color manipulation effects to not be saved within the color
			//as final objects can still be changed.
			this.color = c;
		}
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
	public void setLocationByCenter(int x, int y){
		this.drawX = x - drawWidth / 2;
		this.drawY = y - drawHeight / 2;
	}

	/**
	 * Superior method to Draw.
	 * Render decides if the object is worthy of being drawn and only then calls Draw.
	 * If render returns true then the item was drawn.
	 */
	public boolean render( Draw d ){
		if(isVisible){
			draw( d );
			return true;
		}
		return false;
	}

	public abstract void draw( Draw d );

}