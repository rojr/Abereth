package com.gmail.robmadeyou.gui;

import org.lwjgl.opengl.Drawable;

public abstract class ABContainer extends ABGui {

	public ABContainer(float x, float y){
        super(x, y);
        setHeight(50);
        setWidth(50);
	}
	
	public abstract Drawable getContainerContents();
	
	public abstract void addToContainer(Drawable d);
}
