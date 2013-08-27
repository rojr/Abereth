import java.util.ArrayList;

import com.gmail.robmadeyou.Effects.Animate;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.Emitter;
import com.gmail.robmadeyou.Effects.Emitter.MovementDirection;
import com.gmail.robmadeyou.Effects.TextureLoader;
import com.gmail.robmadeyou.Effects.Textures;
import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Entity.Npc;
import com.gmail.robmadeyou.Entity.Player;
import com.gmail.robmadeyou.Gui.Button;
import com.gmail.robmadeyou.Gui.Interface;
import com.gmail.robmadeyou.Gui.Text;
import com.gmail.robmadeyou.Input.Keyboard;
import com.gmail.robmadeyou.Input.Keyboard.Key;
import com.gmail.robmadeyou.Input.Mouse;
import com.gmail.robmadeyou.Layer;
import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Screen.GameType;

public class Main {
public static void main (String []args){
	
	
		Screen.create(800, 512, "Our Screen", GameType.RPG_STYLE, false);
		Screen.setWorldDimensionsInBlocks(100, 100);
		
		Screen.setUpWorld();
		Player player = new Player(50, 40, 32, 32);
		Engine.addEntity(player);
		// "", 50, 50, 50, 50, 1
		Button button = (Button) Interface.add(new Button("", 50, 50, 50, 50, 1));
		button.useTranslate(true);
		
		Npc enemy = new Npc(32, 40, 32, 32);
        enemy.setLogic(true);
        enemy.setTargetPlayer(player);
		Engine.addEntity(enemy);
		
		//Npc enemy2 = new Npc(20, 40, 32, 32);
		//Engine.addEntity(enemy2);
		
		//MessageArea.setUp(0, 400, 800, 200);
		//MessageArea.setUpTextStart(20, 420);
		
		Emitter emit = new Emitter(0, 0, 50, 0.02f, 1, 0, Color.Green);
		emit.setMovementDirection(MovementDirection.RIGHT);
		Engine.addNewEmitter(emit);
		
		
		ArrayList<Integer> listOfTextures = new ArrayList<Integer>();
		listOfTextures.add(TextureLoader.createTexture("res/Player.png"));
		listOfTextures.add(TextureLoader.createTexture("res/Player.png", 0, 0, 16, 16));
		Animate animTest = new Animate(listOfTextures, 2, 0, true);
		
		while(!Screen.isAskedToClose()){
			//Updating the screen. the maximum frame rate is 60.
			Screen.update(60);
			emit.setX(Mouse.getX());
			emit.setY(Mouse.getY());
			
			enemy.setColor(Color.White);
			enemy.setTexture(Textures.test2);

            player.setTexture(animTest.getTextureID());
			
			if(Keyboard.isKeyPressed(Key.T)){
				if(enemy.isAStarActive()){
					enemy.setAStar(false);
				}else{
					enemy.setAStar(true);
				}
			}
			
			Text.drawString("Translate_X: " + Screen.translate_x + "\nTranslate_Y: "
					+ Screen.translate_y, player.getX() + 50, player.getY(), player.getLayer(), 1, 1, Color.Black, true, false);
			
			if(Engine.isDevMode){
				Text.drawString(Screen.actualFps + "", Mouse.getX() + 10, Mouse.getY(), Layer.GUILayer(), 1, 1, Color.Black, true, false);
                //SATCollisionDetection.getNonEffectingBlocks();
			}
			
			if(button.isReleased()){
				System.out.println("Magic");
			}
			
			//Refreshing the screen
			Screen.refresh();
		}
		Screen.destroy();
	}
}
