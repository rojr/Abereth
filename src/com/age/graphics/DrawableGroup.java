package com.age.graphics;

import java.util.ArrayList;

public abstract class DrawableGroup{
	ArrayList<Drawable> list;
	public DrawableGroup(){
	}
	public void setList(ArrayList d){
		list = d;
	}
	public ArrayList<Drawable> getList(){
		return list;
	}
	public abstract void render();
}
