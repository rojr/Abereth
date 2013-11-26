package com.age;

public abstract class View {
	String name;
	public View(String name) throws Error{
		this.name = name;
	}
	public String getName(){
		return name;
	}
	
	public abstract void render();
	public abstract void update();
	public abstract void dispose();
	
}
