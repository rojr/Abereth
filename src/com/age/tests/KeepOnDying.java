package com.age.tests;

import com.age.Age;
import com.age.Game;
import com.age.Screen;
import com.age.View;
import com.age.graphics.render.shapes.Line;
import com.age.helper.Ascii;
import com.age.helper.Binary;
import com.age.world.TileType;
import com.age.world.World;
import com.age.graphics.effects.Color;
import com.age.graphics.render.shapes.Box;
import com.age.logic.input.Keyboard;
import com.age.logic.input.Mouse;
import com.age.logic.entity.Enemy;
import com.age.logic.entity.Player;
import org.lwjgl.opengl.Display;

/**
 * Reason for the name KeepOnDying is because when I
 * initially started working on the entities, everything would just
 * fall down and "die"
 */
public class KeepOnDying extends View{

    Player en;
    public KeepOnDying(){
        super("KeepOnDying");
    }

    @Override
    public void update() {
        en.setSpeed(10);
        if(Mouse.isLeftMouseButtonDown()){
            World.activeWorld.set(TileType.BRICK, Mouse.getTranslatedX() / World.TILE_DIMENSIONS(), Mouse.getTranslatedY() / World.TILE_DIMENSIONS());
        }else if(Mouse.isRightMouseButtonDown()){
            Enemy e =(Enemy) new Enemy(Mouse.getTranslatedX(), Mouse.getTranslatedY(), 40, 80).toEngine();
            if(Keyboard.isKeyDown(Keyboard.Key.L)){
                World.activeWorld.save("save.txt");
            }
            e.setUseTranslate(true);
        }

        if(Keyboard.isKeyDown(Keyboard.Key.D)){
            en.moveRight();
        }else if(Keyboard.isKeyDown(Keyboard.Key.A)){
            en.moveLeft();
        }
        System.out.println(Ascii.numberToString(Binary.fromBinaryToInt(Binary.toBinary(Ascii.stringToNumber("HO")))));
    }

    @Override
    public void dispose() {

    }

    @Override
    public void init() {
        Box box = (Box) new Box(0,0,Screen.getWidth(),Screen.getHeight()).toEngine();
        en = (Player) new Player(200,200, 20, 50).toEngine();
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
    }

    public static void main(String... args){
        Game g = new Game("KeepOnDying", 500, 500);
        g.start(new KeepOnDying());
    }
}