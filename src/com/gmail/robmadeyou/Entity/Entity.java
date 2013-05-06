package com.gmail.robmadeyou.Entity;

public interface Entity {
	int getNumber();
	void setNumber(int num);
	void setWidth(int w);
	void setHeight(int h);
	void setX(double x);
	void setY(double y);
	void setDX(double dX);
	void setDY(double dY);
	void setSpeed(double speed);
	double getX();
	double getY();
	double getDX();
	double getDY();
	double getSpeed();
	int getWidth();
	int getHeight();
	void onUpdate(int delta);
	void draw();
}
