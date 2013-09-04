import com.gmail.robmadeyou.Effects.*;
import com.gmail.robmadeyou.Effects.Emitter.MovementDirection;
import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Entity.Npc;
import com.gmail.robmadeyou.Entity.Player;
import com.gmail.robmadeyou.Entity.Player.MovementType;
import com.gmail.robmadeyou.Gui.Button;
import com.gmail.robmadeyou.Gui.Interface;
import com.gmail.robmadeyou.Gui.Text;
import com.gmail.robmadeyou.Input.Keyboard;
import com.gmail.robmadeyou.Input.Keyboard.Key;
import com.gmail.robmadeyou.Input.Mouse;
import com.gmail.robmadeyou.Item.Item;
import com.gmail.robmadeyou.Layer;
import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.Target;
import com.gmail.robmadeyou.World.Camera;
import com.gmail.robmadeyou.World.World;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        Screen.create(800, 512, "Our Screen", GameType.RPG_STYLE, false);
        Screen.setWorldDimensionsInBlocks(50, 50);

        Screen.setUpWorld();
        
        Button button = (Button) Interface.add(new Button("", 25, 50, 50, 50, 1));
        button.useTranslate(true);
        
        Player player2 = (Player) Engine.addEntity(new Player(32, 32, 32, 32));
        player2.setFixedMovementType(MovementType.WASD_KEYS);
        Npc enemy = new Npc(32, 40, 32, 32);
        enemy.setLogic(true);
        enemy.setTargetPlayer(player2);
        Engine.addEntity(enemy);

        //Npc enemy2 = new Npc(20, 40, 32, 32);
        //Engine.addEntity(enemy2);
        Item item = Engine.addNewItem(new Item(60, 40, 16, 16,1, Textures.ITEM_TEST));

        //MessageArea.setUp(0, 400, 800, 200);
        //MessageArea.setUpTextStart(20, 420);

        Emitter emit = new Emitter(0, 0, 50, 0.02f, 1, 0, Color.Green);
        emit.setMovementDirection(MovementDirection.RIGHT);
        Engine.addNewEmitter(emit);

        ArrayList<Integer> listOfTextures = new ArrayList<Integer>();
        listOfTextures.add(TextureLoader.createTexture("res/sheet01.png", 192, 64, 32, 32));
        listOfTextures.add(TextureLoader.createTexture("res/sheet01.png", 224, 64, 32, 32));
        Animate animTest = new Animate(listOfTextures, 20, 0, true);
        Layer.addLayer(3);
        
        
        Camera cam = Engine.addNewCamera(new Camera(0, 0, 0, 0, Screen.getWidth(), Screen.getHeight() / 2));
        cam.setFollowingTarget(true);
        cam.setTarget(new Target(player2));
        while (!Screen.isAskedToClose()) {
        	
        	System.out.println(cam.getX() / World.BLOCK_SIZE());
            //Updating the screen. the maximum frame rate is 60.
            Screen.update(60);
            emit.setX(Mouse.getX());
            emit.setY(Mouse.getY());
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
                Text.drawString(Screen.actualFps + "", Mouse.getX() + 10, Mouse.getY(), Layer.GUILayer(), 1, 1, Color.Black, true, false);
            }

            if (button.isReleased()) {
                System.out.println("Magic");
            }
            //Refreshing the screen
            Screen.refresh();
        }
        Screen.destroy();
    }
}
