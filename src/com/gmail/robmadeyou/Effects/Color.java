package com.gmail.robmadeyou.Effects;

import java.util.Random;
import static org.lwjgl.opengl.GL11.glColor3f;

public enum Color {
	
	White(1F,1F,1F), Black(0F, 0F, 0F), Red(1F, 0F, 0F), Green(0F, 1F, 0F), Blue(0F, 0F, 1F), Random(500F, 500F, 500F);
	
	
	
	; 
	
	private float r, g,b;
	Color(float r, float g, float b){
		this.r = r;
		this.g = g;
		this.b = b;
	}
	public void bind(){
		if(r != 500F && g != 500F && b != 500F){
			glColor3f(r,g,b);
		}else{
			Random ran = new Random();
			float rr = ran.nextFloat();
			float rg = ran.nextFloat();
			float rb = ran.nextFloat();
			glColor3f(rr,rg,rb);
		}
	}
}
