package com.gmail.robmadeyou.Gui;

public interface Gui {
	void setLocation(int x, int y);
	void setWidth(int w);
	void setHeight(int h);
	int getX();
	int getY();
	int getWidth();
	int getHeight();
	void onUpdate();
	String getState();
	void addToState(String toAdd);
}
