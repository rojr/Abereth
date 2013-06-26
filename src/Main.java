import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.Entity.Enemy;
import com.gmail.robmadeyou.Entity.Player;
import com.gmail.robmadeyou.Entity.Enemy.EnemyMovement;
import com.gmail.robmadeyou.Input.Keyboard;
import com.gmail.robmadeyou.Input.Keyboard.Key;
import com.gmail.robmadeyou.Input.Mouse;
import com.gmail.robmadeyou.World.World;


public class Main {
public static void main (String []args){
		
		Screen.create(800, 600, "Our Screen", GameType.RPG_STYLE, false);
		Screen.setWorldDimensionsInBlocks(100, 100);
		
		Screen.setUpWorld();
		Player player = new Player(40, 40, 50, 50);
		Engine.addEntity(player);
		player.setLayer(1);
		
		Enemy enemy = new Enemy(0, 40, 50, 50);
		Engine.addEntity(enemy);
		
		while(!Screen.isAskedToClose()){
			//Updating the screen. the maximum frame rate is 60.
			Screen.update(60);
			
			if(Mouse.leftMouseButtonPressed){
				Enemy a = (Enemy) Engine.addEntity(new Enemy(Mouse.getX(),Mouse.getY(),50,50));
				a.orders(EnemyMovement.LEFT, 20);
				a.orders(EnemyMovement.UP, 20);
				a.orders(EnemyMovement.RIGHT, 20);
			}
			if(Keyboard.isKeyPressed(Key.V)){
				Screen.changeGameMode(GameType.SIDE_SCROLLER);
			}
			if(Keyboard.isKeyPressed(Key.C)){
				Screen.changeGameMode(GameType.RPG_STYLE);
			}
			//Refreshing the screen
			Screen.refresh();
		}
		Screen.destroy();
	}
}
