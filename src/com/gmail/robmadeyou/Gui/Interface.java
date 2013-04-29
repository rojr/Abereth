package com.gmail.robmadeyou.Gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.gmail.robmadeyou.State.StateManager;

public class Interface {
	private static int maxButtons = 10;
	private static int maxGuiBoxes = 10;
	private static int maxInputBoxes = 10;
	private static int maxTextBoxes = 10;
	
	private static int biggestArray = maxButtons;
	static Button[] button = new Button[maxButtons];
	
	public static void onUpdate(){
		/*
		 * Here we set the biggest array to be what ever the biggest array is. Doing this I will be able to save some performance hopefully
		 * by only having one for loop running all the GUI arrays rather than having 4 arrays all constantly running
		 */
		if(biggestArray < maxButtons){
			biggestArray = maxButtons;
		}else if(biggestArray < maxGuiBoxes){
			biggestArray = maxGuiBoxes;
		}else if(biggestArray < maxInputBoxes){
			biggestArray = maxInputBoxes;
		}else if(biggestArray < maxTextBoxes){
			biggestArray = maxTextBoxes;
		}
		for(int i = 0; i < biggestArray; i++){
			if(biggestArray < maxButtons){
				if(button[i] != null){
					if(button[i].getState().contains(StateManager.currentState)){
						button[i].onUpdate();
					}
				}
			}else if(biggestArray < maxGuiBoxes){
				//TODO Finish maxGuiBoxes
			}else if(biggestArray < maxInputBoxes){
				//TODO Finish maxInputBoxes
			}else if(biggestArray < maxTextBoxes){
				//TODO Finish maxTextBoxes
			}
		}
	}
	/*
	 * Here we are able to add a button to the screen
	 */
	public static void addButton(int x, int y, int width, int height, Color color, Texture Texture, String state){
		for(int i = 0; i < maxButtons; i++){
			if(button[i] == null){
				button[i] = new Button(x, y, width, height, color, Texture, state);
				button[i].setNumber(i);
				maxButtons++;
				/*
				 * Break there is used so if the new button is added the for 
				 * loop doesn't repeat till i isn't less than maxButtons.
				 */
				break;
			}
		}
	}
}
