import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Input.Mouse;
import com.gmail.robmadeyou.Gui.Interface;
import com.gmail.robmadeyou.Gui.Color;

public class Main {
	public static void main(String []arg){
		
		Screen.createScreen(600, 400, "name");
		
		Interface.addButton("nazi",20, 50, 100, 40, Color.Blue, null, "null");
		Interface.addButton("nazis",200, 50, 100, 40, Color.Blue, null, "null");
		Interface.addButton("nazis2",20, 290, 100, 40, Color.Blue, null, "null");
		
		
		while(!Screen.isAskedToClose()){
			Screen.screenUpdate(60);
			if(Interface.getButtonByName("nazis").isPressed()){
				Interface.getButtonByName("nazi").setHeight(Interface.getButtonByName("nazi").getHeight() + 10);
			}
			if(Interface.getButtonByName("nazis2").isPressed()){
				Interface.getButtonByName("nazi").setHeight(Interface.getButtonByName("nazi").getHeight() - 10);
			}
			if(Interface.getButtonByName("nazi").isMouseOver()){
				Interface.getButtonByName("nazi").setColor(Color.Random);
			}else{
				Interface.getButtonByName("nazi").setColor(Color.Blue);
			}
			if(Interface.getButtonByName("nazi").isReleasedOnButton()){
				Interface.getButtonByName("nazi").setToLocation(60, 60);
			}
		}
	}
}
