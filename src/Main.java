import com.gmail.robmadeyou.Screen;

import com.gmail.robmadeyou.Gui.Interface;
import com.gmail.robmadeyou.Gui.Color;

public class Main {
	public static void main(String []arg){
		Screen.createScreen(600, 400, "name");
		
		Interface.addButton("Blih",20, 50, 100, 40, Color.Blue, null, "null");

		
		Interface.addBox("Blah", 20, 100, 60, 60, null, Color.Blue, "null");
		
		while(!Screen.isAskedToClose()){
			Screen.screenUpdate(60);
		}
	}
}
