package com.age.graphics.ui;

import java.util.ArrayList;

import com.age.graphics.Drawable;
import com.age.graphics.DrawableGroup;

public class GuiContainer extends Gui{

	ArrayList<Drawable> containedObjects = new ArrayList<Drawable>();
	ArrayList<DrawableGroup> containedObjectsDG = new ArrayList<DrawableGroup>();
	public GuiContainer(double drawX, double drawY, double drawWidth, double drawHeight) {
		super(drawX, drawY, drawWidth, drawHeight);
	}
	
	@Override
	public void setDrawX(double x){
		super.setDrawX(x);
		for(Drawable g : containedObjects){
			g.setDrawX(g.getDrawX() + (g.getDrawX() - x));
		}
		for(DrawableGroup dg : containedObjectsDG){
			if(dg instanceof Text){
				((Text) dg).setLocation(x, 50);
			}else{
				for(Drawable d : dg.getList()){
					d.setDrawX(x);
				}
			}
		}
	}
	
	
	/**
	 * Adds an object to the container array list
	 * Returns <b>GuiContainer</b> for easier calling
	 * @param obj
	 */
	public Drawable add(Drawable obj){
			containedObjects.add(obj);
			return obj;
	}
	
	public DrawableGroup add(DrawableGroup obj){
		containedObjectsDG.add(obj);
		return obj;
	}
	/**
	 * Removes objects from the container
	 * @param obj
	 */
	public GuiContainer remove(Drawable obj){
		for(int i = 0; i < containedObjects.size(); i++){
			if(containedObjects.get(i) == obj ){
				containedObjects.remove(i);
				return this;
			}
		}
		return this;
	}
}
