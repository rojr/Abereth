package com.gmail.robmadeyou.Gui;

public class Container extends Gui {

	
	private double x, y, w, h;
	public Container(float x, float y){
        super(x, y);
        this.x = x;
		this.y = y;
		this.w = 50;
		this.h = 50;
	}
	
	
	@Override
	public Gui setX(float x) {
		// TODO Auto-generated method stub
		 return null;
	}

	@Override
	public Gui setY(float y) {
		// TODO Auto-generated method stub
		 return null;
	}

	@Override
	public Gui setWidth(float width) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gui setHeight(float height) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLayer(int layer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Gui setState(String state) {
		// TODO Auto-generated method stub
		 return null;
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void draw() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
	public float getX() {
		return super.getDrawX();
	}

	@Override
	public float getY() {
		return super.getDrawY();
	}

	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return super.getDrawWidth();
	}

	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		return super.getDrawHeight();
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return super.getLayer();
	}

	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMouseOver() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
