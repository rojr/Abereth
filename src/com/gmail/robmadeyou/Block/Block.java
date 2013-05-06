package com.gmail.robmadeyou.Block;

public interface Block {
	int getID();
	boolean isSolid();
	void setX(double x);
	void setY(double y);
	double getX();
	double getY();
	void onUpdate(int delta);
	void draw();
}
