package com.gmail.robmadeyou;

public class Layer {
	public static int layers = 1;
	
	public static int topLayer(){
		return layers - 1;
	}
	public static int GUILayer(){
		return layers;
	}
	public static void addLayer(){
		layers++;
	}
	public static void addLayer(int amount){
		layers += amount;
	}
	public static void onUpdate(){
		
	}
}
