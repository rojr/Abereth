package com.age.tests;

import com.age.Age;
import com.age.Screen;
import com.age.event.EventWorld;
import com.age.graphics.render.shapes.Line;
import com.age.graphics.ui.Container;
import com.age.world.TileType;
import com.age.world.World;
import com.age.graphics.effects.Color;
import com.age.graphics.effects.TextureLoader;
import com.age.graphics.render.shapes.Box;
import com.age.logic.input.Keyboard;
import com.age.logic.input.Mouse;
import com.age.logic.entity.Enemy;
import com.age.logic.entity.Player;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;


public class KeepOnDying {

    public static void main(String... args){

        Screen.create(1200, 600, "KeepOnDying");
        Box box = (Box) new Box(0,0,Screen.getWidth(),Screen.getHeight()).toEngine();

        Player en = (Player) new Player(200,200, 20, 50).toEngine();
        Enemy enemy = (Enemy) new Enemy(300,200,40,60).toEngine();
        enemy.setUseTranslate(true);
        en.setColor(Color.RED);
        en.setLayer(2);
        Display.setVSyncEnabled(true);
        World.load(new World(32, 200, 40));
        en.setUseTranslate(true);
        Age.cameraMain.setTarget(en);
        World.activeWorld.load();
        Line l = new Line(2,0,5090,50000).toEngine();
        l.setLayer(5);
        l.setUseTranslate(true);


        while(!Screen.isCloseRequested()){

            Screen.update();
            if(Mouse.isLeftMouseButtonDown()){
                World.activeWorld.set(TileType.BRICK, Mouse.getTranslatedX() / World.TILE_DIMENSIONS(), Mouse.getTranslatedY() / World.TILE_DIMENSIONS());
            }else if(Mouse.isRightMouseButtonDown()){
                Enemy e =(Enemy) new Enemy(Mouse.getTranslatedX(), Mouse.getTranslatedY(), 40, 80).toEngine();
                if(Keyboard.isKeyDown(Keyboard.Key.L)){
                    World.activeWorld.save("save.txt");
                }
            e.setUseTranslate(true);
        }

        if(Keyboard.isKeyDown(Keyboard.Key.W)){
            en.setVelocityY(en.getSpeed());
        }
            Screen.refresh(60);
        }
    }
}