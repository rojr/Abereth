package com.gmail.robmadeyou.tests;

import com.gmail.robmadeyou.*;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.Effects.Animate;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.Emitter;
import com.gmail.robmadeyou.Effects.TextureLoader;
import com.gmail.robmadeyou.Effects.Textures;
import com.gmail.robmadeyou.Entity.*;
import com.gmail.robmadeyou.Entity.Player.MovementType;
import com.gmail.robmadeyou.Gui.Text;
import com.gmail.robmadeyou.Input.Keyboard;
import com.gmail.robmadeyou.Input.Keyboard.Key;
import com.gmail.robmadeyou.Input.Mouse;
import com.gmail.robmadeyou.Object.ABItem;
import com.gmail.robmadeyou.World.Camera;
import com.gmail.robmadeyou.World.World;

import java.util.ArrayList;

public class Camera_1 {
    
	public static void main(String[] args) {

        Screen.create(800, 800, "Our Screen", GameType.RPG_STYLE, false);
        Screen.setWorldDimensionsInBlocks(50, 50);
        Screen.setUpWorld();
        
        Player player2 = (Player) Engine.addEntity(new Player(32, 32, 32, 32));
        player2.setFixedMovementType(MovementType.WASD_KEYS);
        
        Player player3 = (Player) Engine.addEntity(new Player(32, 32, 32, 32));
        player3.setFixedMovementType(MovementType.ARROW_KEYS);
        player2.setSpeed(3);
        World.gravity(200);
        Npc enemy = new Npc(32, 40, 32, 32);
        enemy.setLogic(true);
        enemy.setTargetPlayer(player2);
        Engine.addEntity(enemy);

        //Npc enemy2 = new Npc(20, 40, 32, 32);
        //Engine.addEntity(enemy2);
        ABItem item = Engine.addNewItem(new ABItem(60, 40, 16, 16));

        //MessageArea.setUp(0, 400, 800, 200);
        //MessageArea.setUpTextStart(20, 420);

        Emitter emit = new Emitter(0, 0, 50);
        emit.setLayer(1);
        emit.setUseTranslate(true);
        Engine.addNewEmitter(emit);

        ArrayList<Integer> listOfTextures = new ArrayList<Integer>();
        listOfTextures.add(TextureLoader.createTexture("res/sheet01.png", 192, 64, 32, 32));
        listOfTextures.add(TextureLoader.createTexture("res/sheet01.png", 224, 64, 32, 32));
        Animate animTest = new Animate(listOfTextures, 20, 0, true);
        Layer.addLayer(3);
        
        Camera cam = Engine.addNewCamera(new Camera(0, 0, 0, 0, Screen.getWidth(), Screen.getHeight()));
        cam.setFollowingTarget(true);
        cam.setTarget(new Target(player2));
        
        while (!Screen.isAskedToClose()) {
        	
            //Updating the screen. the maximum frame rate is 60.
            Screen.update(60);
            emit.setX(Mouse.getTranslatedX());
            emit.setY(Mouse.getTranslatedY());
            enemy.setColor(Color.White);
            enemy.setTexture(Textures.test);
            player2.setTexture(animTest.getTextureID());
            if(item.isPressed()){
            	Engine.removeItem(item);
            }
            if (Keyboard.isKeyPressed(Key.T)) {
                enemy.setAStar(!enemy.isAStarActive());
            }

            if (Engine.isDevMode) {
                Text.drawString(Screen.actualFps + "", (float) Mouse.getTranslatedX() + 10, Mouse.getTranslatedY(), Layer.GUILayer(), 1, 1, Color.Black, true, false);
            }
            if(Mouse.leftMouseButtonPressed){
            	Engine.addNewItem(new ABItem(Mouse.getTranslatedX(), Mouse.getTranslatedY(), 16, 16));
            }
            if(Mouse.leftMouseButtonDown){
            	emit = new Emitter(Mouse.getTranslatedX(), Mouse.getTranslatedY(), 50);
                emit.setLayer(1);
                emit.setUseTranslate(true);
                Engine.addNewEmitter(emit);
            }
            if(Keyboard.isKeyDown(Key.A)){
            	player2.setTextureInverts(true);
            }else if(Keyboard.isKeyDown(Key.D)){
            	player2.setTextureInverts(false);
            }
            //Refreshing the screen
            Screen.refresh();
        }
        Screen.destroy();
    }
}

