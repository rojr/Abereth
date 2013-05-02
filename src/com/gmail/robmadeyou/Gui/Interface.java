package com.gmail.robmadeyou.Gui;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import com.gmail.robmadeyou.State.StateManager;

public class Interface {
	private static int maxButtons = 10;
	private static int maxGuiBoxes = 10;
	private static int maxInputBoxes = 10;
	private static int maxTextBoxes = 10;
	
	private static int biggestArray = maxButtons;
	static ArrayList<Button> button = new ArrayList();
	
	public static void onUpdate(){
		/*
		 * Here we set the biggest array to be what ever the biggest array is. Doing this I will be able to save some performance hopefully
		 * by only having one for loop running all the GUI arrays rather than having 4 arrays all constantly running
		 */
		maxButtons = button.size();
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
			if(i  < maxButtons){
				if(button.get(i) != null){
					if(button.get(i).getState().contains(StateManager.currentState)){
						button.get(i).onUpdate();
					}
				}
			}
			if(biggestArray < maxGuiBoxes){
				//TODO Finish maxGuiBoxes
			}
			if(biggestArray < maxInputBoxes){
				//TODO Finish maxInputBoxes
			}
			if(biggestArray < maxTextBoxes){
				//TODO Finish maxTextBoxes
			}
		}
	}
	/*
	 * Here we are able to add a button to the screen
	 */
	public static void addButton(String name, int x, int y, int width, int height, Color color, Texture Texture, String state){
		button.add(new Button(name, x, y, width, height, color, Texture, state));
		button.get(button.size() - 1).setNumber(button.size() - 1);
		maxButtons++;
	}
	public static Button getButtonByName(String name){
		for(int i = 0; i < button.size(); i++){
			if(button.get(i).getName().equals(name)){
				return button.get(i);
			}
		}
		return null;
	}
}
