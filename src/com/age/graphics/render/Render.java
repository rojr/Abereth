package com.age.graphics.render;

import com.age.graphics.Camera;
import com.age.graphics.Drawable;

public class Render {
	public static void all(Camera cam){
		for(int i = 0; i < Collector.drawArray.size(); i++){
			for(Drawable d : Collector.drawArray.get(i).getList()){
				 d.draw();
			}
		}
	}
}
