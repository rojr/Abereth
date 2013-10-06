package com.gmail.robmadeyou.tests;

import com.gmail.robmadeyou.*;
import com.gmail.robmadeyou.ABScreen.GameType;
import com.gmail.robmadeyou.Effects.ABAnimate;
import com.gmail.robmadeyou.Effects.ABColor;
import com.gmail.robmadeyou.Effects.ABEmitter;
import com.gmail.robmadeyou.Effects.ABTextureLoader;
import com.gmail.robmadeyou.Effects.ABTextures;
import com.gmail.robmadeyou.Entity.*;
import com.gmail.robmadeyou.Entity.ABPlayer.MovementType;
import com.gmail.robmadeyou.Gui.ABText;
import com.gmail.robmadeyou.Input.ABKeyboard;
import com.gmail.robmadeyou.Input.ABKeyboard.ABKey;
import com.gmail.robmadeyou.Input.ABMouse;
import com.gmail.robmadeyou.Object.ABItem;
import com.gmail.robmadeyou.World.ABCamera;
import com.gmail.robmadeyou.World.ABWorld;

import java.util.ArrayList;

public class Camera_1 {
    
	public static void main(String[] args) {

        ABScreen.create(800, 800, "Our Screen", GameType.RPG_STYLE, false);
        ABScreen.setWorldDimensionsInBlocks(50, 50);
        ABScreen.setUpWorld();
        
        ABPlayer player2 = (ABPlayer) Abereth.addEntity(new ABPlayer(32, 32, 32, 32));
        player2.setFixedMovementType(MovementType.WASD_KEYS);
        
        ABPlayer player3 = (ABPlayer) Abereth.addEntity(new ABPlayer(32, 32, 32, 32));
        player3.setFixedMovementType(MovementType.ARROW_KEYS);
        player2.setSpeed(3);
        ABWorld.gravity(200);
        ABNpc enemy = new ABNpc(32, 40, 32, 32);
        enemy.setLogic(true);
        enemy.setTargetPlayer(player2);
        Abereth.addEntity(enemy);

        //Npc enemy2 = new Npc(20, 40, 32, 32);
        //Engine.addEntity(enemy2);
        ABItem item = Abereth.addNewItem(new ABItem(60, 40, 16, 16));

        //MessageArea.setUp(0, 400, 800, 200);
        //MessageArea.setUpTextStart(20, 420);

        ABEmitter emit = new ABEmitter(0, 0, 50);
        emit.setLayer(1);
        emit.setUseTranslate(true);
        Abereth.addNewEmitter(emit);

        ArrayList<Integer> listOfTextures = new ArrayList<Integer>();
        listOfTextures.add(ABTextureLoader.createTexture("res/sheet01.png", 192, 64, 32, 32));
        listOfTextures.add(ABTextureLoader.createTexture("res/sheet01.png", 224, 64, 32, 32));
        ABAnimate animTest = new ABAnimate(listOfTextures, 20, 0, true);
        ABLayer.addLayer(3);
        
        ABCamera cam = Abereth.addNewCamera(new ABCamera(0, 0, 0, 0, ABScreen.getWidth(), ABScreen.getHeight()));
        cam.setFollowingTarget(true);
        cam.setTarget(new ABTarget(player2));
        
        while (!ABScreen.isAskedToClose()) {
        	
            //Updating the screen. the maximum frame rate is 60.
            ABScreen.update(60);
            emit.setX(ABMouse.getTranslatedX());
            emit.setY(ABMouse.getTranslatedY());
            enemy.setColor(ABColor.White);
            enemy.setTexture(ABTextures.test);
            player2.setTexture(animTest.getTextureID());
            if(item.isPressed()){
            	Abereth.removeItem(item);
            }
            if (ABKeyboard.isKeyPressed(ABKey.T)) {
                enemy.setAStar(!enemy.isAStarActive());
            }

            if (Abereth.isDevMode) {
                ABText.drawString(ABScreen.actualFps + "", (float) ABMouse.getTranslatedX() + 10, ABMouse.getTranslatedY(), ABLayer.GUILayer(), 1, 1, ABColor.Black, true, false);
            }
            if(ABMouse.leftMouseButtonPressed){
            	Abereth.addNewItem(new ABItem(ABMouse.getTranslatedX(), ABMouse.getTranslatedY(), 16, 16));
            }
            if(ABMouse.leftMouseButtonDown){
            	emit = new ABEmitter(ABMouse.getTranslatedX(), ABMouse.getTranslatedY(), 50);
                emit.setLayer(1);
                emit.setUseTranslate(true);
                Abereth.addNewEmitter(emit);
            }
            if(ABKeyboard.isKeyDown(ABKey.A)){
            	player2.setTextureInverts(true);
            }else if(ABKeyboard.isKeyDown(ABKey.D)){
            	player2.setTextureInverts(false);
            }
            //Refreshing the screen
            ABScreen.refresh();
        }
        ABScreen.destroy();
    }
}

