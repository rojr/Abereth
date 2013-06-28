package com.gmail.robmadeyou.Gui;

public interface Gui {
	
	void setX(double x);
	void setY(double y);
	void setWidth(int width);
	void setHeight(int height);
	void setLayer(int layer);
	
	double getX();
	double getY();
	int getWidth();
	int getHeight();
	int getLayer();
	
	boolean isMouseOver();
}
