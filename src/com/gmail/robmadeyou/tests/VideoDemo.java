package com.gmail.robmadeyou.tests;

import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Effects.*;
import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Entity.Npc;
import com.gmail.robmadeyou.Entity.Player;
import com.gmail.robmadeyou.Entity.Player.MovementType;
import com.gmail.robmadeyou.Gui.Text;
import com.gmail.robmadeyou.Input.Keyboard;
import com.gmail.robmadeyou.Input.Keyboard.Key;
import com.gmail.robmadeyou.Input.Mouse;
import com.gmail.robmadeyou.Layer;
import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Object.ABItem;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.Target;
import com.gmail.robmadeyou.World.Camera;

import java.util.ArrayList;


public class VideoDemo {
    
	public static void main(String[] args) {

        Screen.create(800, 800, "Our Screen", GameType.RPG_STYLE, false);
        Screen.setWorldDimensionsInBlocks(50, 50);
        Screen.setUpWorld();
        
        Player player2 = (Player) Engine.addEntity(new Player(32, 32, 32, 32));
        player2.setFixedMovementType(MovementType.WASD_KEYS);
        player2.setSpeed(3);
        
        Npc enemy = new Npc(32, 40, 32, 32);
        enemy.setLogic(true);
        enemy.setTargetPlayer(player2);
        Engine.addEntity(enemy);
        ArrayList<Integer> listOfTextures = new ArrayList<Integer>();
        listOfTextures.add(TextureLoader.createTexture("res/sheet01.png", 192, 64, 32, 32));
        listOfTextures.add(TextureLoader.createTexture("res/sheet01.png", 224, 64, 32, 32));
        Layer.addLayer(3);
        
        Camera cam = Engine.addNewCamera(new Camera(0, 0, 0, 0, Screen.getWidth(), Screen.getHeight()));
        cam.setFollowingTarget(true);
        cam.setTarget(new Target(player2));
        Emitter emit = new Emitter(player2.getX(), player2.getY(), 50, 0.02f, 1, 0, Color.White);
        emit.setLayer(2);
        emit.setRepeat(false);
        Engine.addNewEmitter(emit);
        
        while (!Screen.isAskedToClose()) {
            Screen.update(60);
            if (Keyboard.isKeyPressed(Key.T)) {
                enemy.setAStar(!enemy.isAStarActive());
            }
            if(Mouse.leftMouseButtonDown){
            emit = new Emitter(player2.getX(), player2.getY(), 50, 0.02f, 1, 0, Color.White);
            emit.setLayer(2);
            emit.setRepeat(false);
            Engine.addNewEmitter(emit);
            }
            if (Engine.isDevMode) {
                Text.drawString(Screen.actualFps + "", (float) Mouse.getTranslatedX() + 10, Mouse.getTranslatedY(), Layer.GUILayer(), 1, 1, Color.Black, true, false);
            }
            if(Mouse.leftMouseButtonPressed){
            	Engine.addNewItem(new ABItem(Mouse.getTranslatedX(), Mouse.getTranslatedY(), 16, 16));
            }
            if(Keyboard.isKeyDown(Key.A)){
            	player2.setTextureInverts(true);
            }else if(Keyboard.isKeyDown(Key.D)){
            	player2.setTextureInverts(false);
            }
            //Refreshing the screen
            System.out.println(Collector.drawArray.get(2).getList().size());
            Screen.refresh();
        }
        Screen.destroy();
    }
}

