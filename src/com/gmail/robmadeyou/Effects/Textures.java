package com.gmail.robmadeyou.Effects;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

import com.gmail.robmadeyou.Effects.TextureLoader;
public class Textures {
	
	public static boolean texSetUp = false;
	
	public static Texture none;
	public static int test;
	public static int player_default;
	public static int Block_Sky;
	public static int Block_Stone;
	
	public static void setUpTextures(){
		
		try {
			none = org.newdawn.slick.opengl.TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/none.png"), true);
			texSetUp = true;
			System.out.println("Yay");
		} catch (IOException e) {e.printStackTrace();}
		
		test = TextureLoader.createTexture("res/powerUp/PaddleFreeze.png");
		Block_Sky = TextureLoader.createTexture("res/World/Sky.png");
		Block_Stone = TextureLoader.createTexture("res/World/Stone.png");
		
	}
	public static void addTexture(){
		
	}
}
