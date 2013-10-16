package com.gmail.robmadeyou.tests;

import com.gmail.robmadeyou.*;
import com.gmail.robmadeyou.ABScreen.GameType;
import com.gmail.robmadeyou.effects.ABAnimate;
import com.gmail.robmadeyou.effects.ABColor;
import com.gmail.robmadeyou.effects.ABEmitter;
import com.gmail.robmadeyou.effects.ABTextureLoader;
import com.gmail.robmadeyou.effects.ABTextures;
import com.gmail.robmadeyou.entity.*;
import com.gmail.robmadeyou.entity.ABPlayer.MovementType;
import com.gmail.robmadeyou.gui.ABText;
import com.gmail.robmadeyou.object.ABItem;
import com.gmail.robmadeyou.peripherals.ABKeyboard;
import com.gmail.robmadeyou.peripherals.ABMouse;
import com.gmail.robmadeyou.peripherals.ABKeyboard.ABKey;
import com.gmail.robmadeyou.world.ABCamera;
import com.gmail.robmadeyou.world.ABWorld;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

public class Camera_1 {
    
	public static void main(String[] args) {

        ABScreen.create(1024, 600, "Our Screen", GameType.RPG_STYLE, false);
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

        ABEmitter emit = new ABEmitter(0, 0, 10);
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
        
        ABCamera cam2 = Abereth.addNewCamera(new ABCamera(0,0,0,0, 300, 300));
        cam.setFollowingTarget(true);
        cam.setTarget(new ABTarget(enemy));
        while (!ABScreen.isAskedToClose()) {
        	
            //Updating the screen. the maximum frame rate is 60.
            ABScreen.update(60);
            player2.setTexture(animTest.getTextureID());
            if(item.isPressed()){
            	Abereth.removeItem(item);
            }
            
            if (ABKeyboard.isKeyPressed(ABKey.T)) {
                enemy.setAStar(!enemy.isAStarActive());
            }
            if(ABKeyboard.isKeyPressed(ABKey.One)){
            	cam.setTarget(new ABTarget(player2));
            }else if(ABKeyboard.isKeyPressed(ABKey.Two)){
            	cam.setTarget(new ABTarget(enemy));
            }

            if (Abereth.isDevMode) {
                ABText.drawString(ABScreen.actualFps + "", (float) ABMouse.getTranslatedX() + 10, ABMouse.getTranslatedY(), ABLayer.GUILayer(), 1, 1, ABColor.Black, true, false);
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

