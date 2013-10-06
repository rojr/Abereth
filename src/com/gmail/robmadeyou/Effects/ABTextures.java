package com.gmail.robmadeyou.Effects;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

public class ABTextures {

    public static boolean texSetUp = false;

    public static Texture none;
    public static int test;
    public static int test2;
    public static int player_default;
    public static int Block_Sky;
    public static int Block_Stone;
    public static int Block_Gravel;
    public static int ITEM_TEST;

    public static void setUpTextures() {

        try {
            none = org.newdawn.slick.opengl.TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/none.png"), true);
            texSetUp = true;
            System.out.println("Yay");
        } catch (IOException e) {
            e.printStackTrace();
        }

        test = ABTextureLoader.createTexture("res/player/player1.png");
        test2 = ABTextureLoader.createTexture("res/powerUp/PaddleFreeze.png");
        Block_Sky = ABTextureLoader.createTexture("res/World/Sky.png");
        Block_Stone = ABTextureLoader.createTexture("res/World/Stone.png");
        Block_Gravel = ABTextureLoader.createTexture("res/World/Gravel.png");
        ITEM_TEST = ABTextureLoader.createTexture("res/powerUp/PaddleFreeze.png");


    }

    public static void addTexture() {

    }
}
