package com.gmail.robmadeyou.State;

public class StateManager {
	
	static int maxStates = 10;
	public static String currentState = "null";
	static StateArray[] states = new StateArray[maxStates];
	class StateArray{
		String name;
		public StateArray(String name){
			this.name = name;
		}
		/*
		 * If this state's name is equal to the global state that is currently set then
		 * this method will be run. Updating Gui and other things.
		 */
		public void doAction(){
			
		}
	}
	
	public static void addState(String Name_ff_state){
		
	}
}
