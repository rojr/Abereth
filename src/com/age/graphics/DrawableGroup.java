package com.age.graphics;

import java.util.ArrayList;

public abstract class DrawableGroup{
	ArrayList<Drawable> list;
	private boolean useTranslate;
	public DrawableGroup(){
		useTranslate = false;
	}
	public void setList(ArrayList d){
		list = d;
		setUseTranslate(useTranslate);
	}
	public ArrayList<Drawable> getList(){
		return list;
	}
	
	public boolean useTranslate(){
		return useTranslate;
	}
	public void setUseTranslate(boolean translate){
		this.useTranslate = translate;
		for(Drawable l : list){
			l.setUseTranslate(useTranslate);
		}
	}
	
	public abstract void render();
}
