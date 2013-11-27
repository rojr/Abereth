package com.age;

import java.util.ArrayList;

import com.age.graphics.Camera;

public class Age {
	public final static String name = "Abereth game engine";
	public final static String version = "0.0.1";
	
	public static boolean rendering = true;
	/**
	 * Would be best advised best to <b>NOT</b> to touch this
	 */
	public static ArrayList<Camera> cameraList = new ArrayList<Camera>();
	public static Camera cameraMain = new Camera(0, 0, Screen.getWidth(), Screen.getHeight());
	
	static ArrayList<View> viewList = new ArrayList<View>();
	public static void addView(View v){
		for(View view: viewList){
			if(v.getName().toLowerCase().equals(view.getName().toLowerCase())){
				
			}
		}
	}
	
	public static void init(){//booiiiiii
		cameraList.add(cameraMain);
	}
	
	public static void onUpdate(){
	
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
