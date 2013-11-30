package com.age.graphics.ui;

import java.util.ArrayList;

public class Container extends Gui{

	
	private ArrayList<Gui> contents = new ArrayList<Gui>();
	public Container(double drawX, double drawY, double drawWidth,double drawHeight) {
		super(drawX, drawY, drawWidth, drawHeight);
	}

	public void add(Gui item){
		contents.add(item);
	}
	
	public void remove(Gui item){
		for(Gui c : contents){
			
		}
	}
	
}
