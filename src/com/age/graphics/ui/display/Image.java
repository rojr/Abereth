package com.age.graphics.ui.display;

import com.age.graphics.ui.GuiContainer;
import com.age.graphics.ui.Text;

public class Image extends GuiContainer{

	private boolean fixDimensionsToText;
	private Text text;
	public Image(double drawX, double drawY, double drawWidth, double drawHeight){
		super(drawX, drawY, drawWidth, drawHeight);
		this.fixDimensionsToText = false;
		this.text = (Text) add(new Text("", drawX, drawY));
	}
	
	public void text(String text){
		this.text.setText(text);
		if(fixDimensionsToText){
			setDrawWidth(this.text.getTextWidth());
			setDrawHeight(this.text.getTextHeight());
		}
	}
	public String text(){
		return text.getText();
	}
	@Override
	public void render(){
		super.render();
		text.render();
	}
}
