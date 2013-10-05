package com.gmail.robmadeyou.tests;
import com.gmail.robmadeyou.Draw.Collector.DrawParameters;
import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Draw.Line;
import com.gmail.robmadeyou.Effects.*;
import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Entity.Npc;
import com.gmail.robmadeyou.Entity.Player;
import com.gmail.robmadeyou.Entity.Player.MovementType;
import com.gmail.robmadeyou.Gui.Button;
import com.gmail.robmadeyou.Gui.Gui;
import com.gmail.robmadeyou.Gui.Interface;
import com.gmail.robmadeyou.Gui.Text;
import com.gmail.robmadeyou.Input.Keyboard;
import com.gmail.robmadeyou.Input.Keyboard.Key;
import com.gmail.robmadeyou.Input.Mouse;
import com.gmail.robmadeyou.Layer;
import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Object.Item;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.Target;
import com.gmail.robmadeyou.World.Camera;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Camera_2 {
    public static void main(String[] args) {
        Screen.create(300, 738, "Our Screen", GameType.RPG_STYLE, false);
        Screen.setWorldDimensionsInBlocks(0,0);
        
        Screen.setUpWorld();
        
        Player player2 = (Player) Engine.addEntity(new Player(32, 32, 32, 32));
        player2.setFixedMovementType(MovementType.WASD_KEYS);
        player2.setSpeed(3);
        Player player3 = (Player) Engine.addEntity(new Player(100, 32, 32, 32));
        player3.setFixedMovementType(MovementType.ARROW_KEYS);
        
        Npc enemy = new Npc(32, 40, 32, 32);
        enemy.setLogic(true);
        enemy.setTargetPlayer(player2);
        Engine.addEntity(enemy);

        //Npc enemy2 = new Npc(20, 40, 32, 32);
        //Engine.addEntity(enemy2);
        Item item = Engine.addNewItem(new Item(60, 40, 16, 16));

        //MessageArea.setUp(0, 400, 800, 200);
        //MessageArea.setUpTextStart(20, 420);

        ArrayList<Integer> listOfTextures = new ArrayList<Integer>();
        listOfTextures.add(TextureLoader.createTexture("res/sheet01.png", 192, 64, 32, 32));
        listOfTextures.add(TextureLoader.createTexture("res/sheet01.png", 224, 64, 32, 32));
        Animate animTest = new Animate(listOfTextures, 20, 0, true);
        Layer.addLayer(3);
        
     
        
        Camera cam = Engine.addNewCamera(new Camera(0, 0, 0, 0, Screen.getWidth() / 2, Screen.getHeight()));
        cam.setFollowingTarget(true);
        cam.setTarget(new Target(player2));
        
        boolean camsCreated = false;
        
        while (!Screen.isAskedToClose()) {
            //Updating the screen. the maximum frame rate is 60.
            Screen.update(60);
            enemy.setColor(Color.White);
            enemy.setTexture(Textures.test);
            System.out.println(Screen.getWidth());
            if(Keyboard.isKeyDown(Keyboard.Key.A)){
            	animTest.setInvert(true);
            }else{
            	animTest.setInvert(false);
            }
            player2.setTexture(animTest.getTextureID());
            
            if(item.isPressed()){
            	Engine.removeItem(item);
            }
            if (Keyboard.isKeyPressed(Key.T)) {
                enemy.setAStar(!enemy.isAStarActive());
            }

            if (Engine.isDevMode) {
                Text.drawString(Screen.actualFps + "  Hel^4lo!awq^3awd^rawdaw^9da^5wda^ndwadwd",(float) Mouse.getTranslatedX() + 10, Mouse.getTranslatedY(), Layer.GUILayer(), 1, 1, Color.Banana, true, false);
            }
            if(Mouse.leftMouseButtonDown){
            	Engine.addNewItem(new Item(Mouse.getTranslatedX(),Mouse.getTranslatedY(), 32, 32));
            }

            //Refreshing the screen
            DrawParameters p = new DrawParameters("line", Screen.getWidth() / 2, 0, Screen.getWidth() / 2, Screen.getHeight());
            p.setLayer(Layer.GUILayer());
            Collector.add(p);
            Screen.refresh();
        }
        Screen.destroy();
    }
}
