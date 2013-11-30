package com.age;

import java.util.ArrayList;

import com.age.graphics.Camera;
import com.age.graphics.effects.TextureLoader;

public class Age {
	public final static String name = "Abereth game engine";
	public final static String version = "0.0.1";
	
	public static int EmptyTexture = -1;
	
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
	
	public static float ratioX(){
		return Screen.getWidth() / Screen.originalDimensionX;
	}
	public static float ratioY(){
		return Screen.getHeight() / Screen.originalDimensionY;
	}
	
	
	/*
	 * Resources
	 */
	
			/*
			 * Items
			 */
				public static char letters[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
					'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
					'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
					'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
					'v', 'w', 'x', 'y', 'z' };

				public static char numbers[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
				
				public static ArrayList<Integer> letterTexID = new ArrayList<Integer>();

				public static ArrayList<Integer> numberTexID = new ArrayList<Integer>();

				public static void loadTextures() {
					for (int i = 0; i < letters.length / 2; i++) {
						letterTexID.add(TextureLoader.createTexture("res/font/sprite1.png",
								i * 8, 0, 8, 8));
					}
					for (int i = 0; i < letters.length / 2; i++) {
							letterTexID.add(TextureLoader.createTexture("res/font/sprite1.png",
									i * 8, 8, 8, 8));
					}
					for (int i = 0; i < numbers.length; i++) {
						numberTexID.add(TextureLoader.createTexture("res/font/sprite1.png",
								i * 8, 16, 8, 8));
					}
				}
	
	public static void init(){//booiiiiii
		EmptyTexture = TextureLoader.createTexture("res/none.png");
		cameraList.add(cameraMain);
		loadTextures();
	}
	
	
	public static void onUpdate(){
	
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
