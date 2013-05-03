package com.gmail.robmadeyou.Gui;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.State.StateManager;

public class Interface {
	private static int maxButtons = 10;
	private static int maxGuiBoxes = 10;
	private static int maxInputBoxes = 10;
	private static int maxTextBoxes = 10;
	
	private static int biggestArray = maxButtons;
	static ArrayList<Button> button = new ArrayList<Button>();
	static ArrayList<Box> box = new ArrayList<Box>();
	
	public static void onUpdate(){
		/*
		 * Here we set the biggest array to be what ever the biggest array is. Doing this I will be able to save some performance hopefully
		 * by only having one for loop running all the GUI arrays rather than having 4 arrays all constantly running
		 */
		//Please, every time you see me call me stupid.
		
		if(biggestArray < button.size()){
			biggestArray = button.size();
		}
		if(biggestArray < box.size()){
			biggestArray = box.size();
		}
		if(biggestArray < maxInputBoxes){
			biggestArray = maxInputBoxes;
		}
		if(biggestArray < maxTextBoxes){
			biggestArray = maxTextBoxes;
		}
		/*
		 * Updating each array, only using one for loop to save 
		 * some performance :p
		 */
		for(int i = 0; i < biggestArray; i++){
			if(i  < button.size()){
				if(button.get(i) != null){
					if(button.get(i).getState().contains(StateManager.currentState)){
						button.get(i).onUpdate();
					}
				}
			}
			if(i < box.size()){
				if(box.get(i) != null){
					if(box.get(i).getState().contains(StateManager.currentState)){
						box.get(i).onUpdate();
					}
				}
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
		boolean nameExists = false;
		for(int i = 0; i < button.size(); i++){
			if(button.get(i).getName().toLowerCase().equals(name)){
				nameExists = true;
				System.out.println(Screen.engineName + "Could not create BUTTON by name of: " + name + " as it already exists.");
				break; //Break here in case array is 10000000k big and doesn't have to loop through everything
			}
		}
		if(!nameExists){
			button.add(new Button(name, x, y, width, height, color, Texture, state));
			button.get(button.size() - 1).setNumber(button.size() - 1);
			maxButtons++;
		}
	}
	public static void addBox(String name, int x, int y, int width, int height, Texture texture, Color color, String state){
		boolean nameExists = false;
		for(int i = 0; i < box.size(); i++){
			if(box.get(i).getName().toLowerCase().equals(name)){
				nameExists = true;
				System.out.println(Screen.engineName + "Could not create BOX by name of: " + name + " as it already exists.");
				break; //Break here in case array is 10000000k big and doesn't have to loop through everything
			}
		}
		if(!nameExists){
			box.add(new Box(name, x, y, width, height, texture, color, state));
			box.get(box.size() - 1).setNumber(box.size() - 1);
			maxGuiBoxes++;
		}
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
