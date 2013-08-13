package com.gmail.robmadeyou.Effects;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
public class Textures {
	
	public static boolean texSetUp = false;
	
	public static Texture none;
	public static int test;
    public static int test2;
	public static int player_default;
	public static int Block_Sky;
	public static int Block_Stone;
	
	public static void setUpTextures(){
		
		try {
			none = org.newdawn.slick.opengl.TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/none.png"), true);
			texSetUp = true;
			System.out.println("Yay");
		} catch (IOException e) {e.printStackTrace();}

        test = TextureLoader.createTexture("res/player/player1.png");
		test2 = TextureLoader.createTexture("res/powerUp/PaddleFreeze.png");
		Block_Sky = TextureLoader.createTexture("res/World/Sky.png");
		Block_Stone = TextureLoader.createTexture("res/World/Stone.png");
		
	}
	public static void addTexture(){
		
	}
}
