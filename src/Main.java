import org.lwjgl.Sys;

import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Layer;
import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.Emitter;
import com.gmail.robmadeyou.Effects.Emitter.MovementDirection;
import com.gmail.robmadeyou.Effects.TextDraw;
import com.gmail.robmadeyou.Entity.Npc;
import com.gmail.robmadeyou.Entity.Player;
import com.gmail.robmadeyou.Entity.Npc.EnemyMovement;
import com.gmail.robmadeyou.Gui.Button;
import com.gmail.robmadeyou.Gui.Interface;
import com.gmail.robmadeyou.Gui.MessageArea;
import com.gmail.robmadeyou.Gui.Text;
import com.gmail.robmadeyou.Input.Keyboard;
import com.gmail.robmadeyou.Input.Mouse;
import com.gmail.robmadeyou.Input.Keyboard.Key;


public class Main {
public static void main (String []args){
		
		Screen.create(800, 512, "Our Screen", GameType.SIDE_SCROLLER, false);
		
		Screen.toggleVSync();
		Screen.setUpWorld();
		Player player = new Player(40, 40, 32, 32);
		Engine.addEntity(player);
		player.setLayer(1);
		
		Npc enemy = new Npc(0, 40, 32, 32);
		Engine.addEntity(enemy);
		
		Npc enemy2 = new Npc(20, 40, 32, 32);
		Engine.addEntity(enemy2);
		
		Button button = (Button) Interface.add(new Button("", 50, 50, 50, 50, 1));
		button.useTranslate(true);
		
		
		MessageArea.setUp(0, 400, 800, 200);
		MessageArea.setUpTextStart(20, 420);
		
		Emitter emit = new Emitter(0, 0, 50, 0.02f, 1, 0, Color.Green);
		emit.setMovementDirection(MovementDirection.RIGHT);
		Engine.addNewEmitter(emit);
		
		while(!Screen.isAskedToClose()){
			//Updating the screen. the maximum frame rate is 60.
			Screen.update(60);
			emit.setX(Mouse.getX());
			emit.setY(Mouse.getY());
			
			//Refreshing the screen
			Screen.refresh();
		}
		Screen.destroy();
	}
}
