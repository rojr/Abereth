import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.Entity.Enemy;
import com.gmail.robmadeyou.Entity.Player;
import com.gmail.robmadeyou.Input.Mouse;
import com.gmail.robmadeyou.World.World;


public class Main {
public static void main (String []args){
		
		Screen.create(800, 600, "Our Screen", GameType.RPG_STYLE, false);
		Screen.setWorldDimensionsInBlocks(100, 100);
		
		Screen.setUpWorld();
		Player player = new Player(40, 40, 50, 50);
		Engine.addEntity(player);
		
		Enemy enemy = new Enemy(0, 40, 50, 50);
		Engine.addEntity(enemy);
		
		while(!Screen.isAskedToClose()){
			//Updating the screen. the maximum frame rate is 60.
			Screen.update(60);
			
			if(Mouse.leftMouseButtonPressed){
				Engine.addEntity(new Enemy(Mouse.getX(),Mouse.getY(),50,50));
			}
			//Refreshing the screen
			Screen.refresh();
		}
		Screen.destroy();
	}
}
