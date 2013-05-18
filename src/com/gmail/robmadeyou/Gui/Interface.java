package com.gmail.robmadeyou.Gui;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.State.State;
import com.gmail.robmadeyou.State.StateManager;

public class Interface {
	private static int maxButtons = 10;
	private static int maxGuiBoxes = 10;
	private static int maxInputBoxes = 10;
	private static int maxTextBoxes = 10;
	
	private static int biggestArray = maxButtons;
	static ArrayList<Button> button = new ArrayList<Button>();
	static ArrayList<Box> box = new ArrayList<Box>();
	static ArrayList<InputBox> inputBox = new ArrayList<InputBox>();
	static ArrayList<TextBox> textBox = new ArrayList<TextBox>();
	
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
		if(biggestArray < inputBox.size()){
			biggestArray = inputBox.size();
		}
		if(biggestArray < textBox.size()){
			biggestArray = textBox.size();
		}
		/*
		 * Updating each array, only using one for loop to save 
		 * some performance :p
		 */
		for(int i = 0; i < biggestArray; i++){
			if(i  < button.size()){
				if(button.get(i) != null){
					if(button.get(i).getState().contains(StateManager.currentState.name())){
						button.get(i).onUpdate();
					}
				}
			}
			if(i < box.size()){
				if(box.get(i) != null){
					if(box.get(i).getState().contains(StateManager.currentState.name())){
						box.get(i).onUpdate();
					}
				}
			}
			if(i < inputBox.size()){
				if(inputBox.get(i) != null){
					if(inputBox.get(i).getState().contains(StateManager.currentState.name())){
						inputBox.get(i).onUpdate();
					}
				}
			}
			if(i < textBox.size()){
				if(textBox.get(i) != null){
					if(textBox.get(i).getState().contains(StateManager.currentState.name())){
						textBox.get(i).onUpdate();
					}
				}
			}
		}
	}
	/*
	 * Here we are able to add a button to the screen
	 */
	public static void addButton(String name, int x, int y, int width, int height, Color color, Texture Texture, State state){
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
	public static void addBox(String name, int x, int y, int width, int height, Texture texture, Color color, State state){
		boolean nameExists = false;
		for(int i = 0; i < box.size(); i++){
			if(box.get(i).getName().equals(name)){
				nameExists = true;
				System.out.println(Screen.engineName + "Could not create BOX by the name of: " + name + " as it already exists.");
				break; //Break here in case array is 10000000k big and doesn't have to loop through everything
			}
		}
		if(!nameExists){
			box.add(new Box(name, x, y, width, height, texture, color, state));
			box.get(box.size() - 1).setNumber(box.size() - 1);
			maxGuiBoxes++;
		}
	}
	public static void addInputBox(String name, int x, int y, int width, int height, Color color, State state, int maxCharacters){
		boolean nameExists = false;
		for(int i = 0; i < inputBox.size(); i++){
			if(inputBox.get(i).getName().contains(name)){
				nameExists = true;
				System.out.println(Screen.engineName + "Could not create INPUTBOX by the name of: " + name + " as it already exists.");
				break; //Break here in case array is 10000000k big and doesn't have to loop through everything
			}
		}
		if(!nameExists){
			inputBox.add(new InputBox(x, y, width, height, name, color, state, maxCharacters));
			inputBox.get(inputBox.size() - 1).setNumber(inputBox.size() -1);
			maxInputBoxes++;
		}
	}
	public static void addTextBox(String text, int x, int y, double size, State state, String name, Color textColor){
		boolean nameExists = false;
		for(int i = 0; i < textBox.size(); i++){
			if(textBox.get(i).getName().contains(name)){
				nameExists = true;
				System.out.println(Screen.engineName + "Could not create TEXTBOX by the name of: " + name + " as it already exists.");
				break;
			}
		}
		if(!nameExists){
			textBox.add(new TextBox(text, x, y, size, state, name, textColor));
			textBox.get(textBox.size() - 1).setNumber(textBox.size() - 1);
			maxTextBoxes++;
		}
	}
	public static Button getButtonByName(String name){
		for(int i = 0; i < button.size(); i++){
			if(button.get(i).getName().equals(name)){
				return button.get(i);
			}
		}
		System.out.println(Screen.engineName + "ERROR; UNABLE TO FIND BUTTON BY THE NAME OF: " + name);
		return null;
	}
	public static Box getBoxByName(String name){
		for(int i = 0; i < box.size(); i++){
			if(box.get(i).getName().equals(name)){
				return box.get(i);
			}
		}
		System.out.println(Screen.engineName + "ERROR; UNABLE TO FIND BOX BY THE NAME OF: " + name);
		return null;
	}
	public static InputBox getInputBoxByName(String name){
		for(int i = 0; i < inputBox.size(); i++){
			if(inputBox.get(i).getName().equals(name)){
				return inputBox.get(i);
			}
		}
		System.out.println(Screen.engineName + "ERROR; UNABLE TO FIND INPUT BOX BY THE NAME OF: " + name);
		return null;
	}
	public static TextBox getTextBoxByName(String name){
		for(int i = 0; i < textBox.size(); i++){
			if(textBox.get(i).getName().equals(name)){
				return textBox.get(i);
			}
		}
		System.out.println(Screen.engineName + "ERROR; UNABLE TO FIND TEXT BOX BY THE NAME OF: " + name);
		return null;
	}
}
