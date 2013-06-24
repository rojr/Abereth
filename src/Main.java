import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.Emitter;
import com.gmail.robmadeyou.Effects.Emitter.MovementDirection;
import com.gmail.robmadeyou.Entity.Player;
import com.gmail.robmadeyou.Input.Keyboard;
import com.gmail.robmadeyou.Input.Keyboard.Key;
import com.gmail.robmadeyou.Input.Mouse;


public class Main {
public static void main (String []args){
		
		Screen.create(800, 600, "Our Screen", GameType.SIDE_SCROLLER, false);
		
		Screen.setUpWorld();
		Player player = new Player(40, 40, 50, 50);
		Engine.addEntity(player);
		
		while(!Screen.isAskedToClose()){
			//Updating the screen. the maximum frame rate is 60.
			Screen.update(60);
			
			if(Keyboard.isKeyDown(Key.A)){
				Emitter emit = new Emitter(Mouse.getX(), Mouse.getY(), 40, 0.02f, 4f, 0, Color.Blue);
				emit.setMovementDirection(MovementDirection.UP);
				Engine.addNewEmitter(emit);
				System.out.println("aa");
			}
			if(Keyboard.isKeyDown(Key.B)){
				Engine.addNewEmitter(new Emitter(Mouse.getX(), Mouse.getY(), 40, 0.02f, 4f, 0, Color.Red));
				System.out.println("bb");
			}
			
			//Refreshing the screen
			Screen.refresh();
		}
		Screen.destroy();
	}
}
