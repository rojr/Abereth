package com.gmail.robmadeyou.Gui;

import com.gmail.robmadeyou.Screen;

import java.util.ArrayList;

public class Interface {
	
	public static ArrayList<Gui> visibleObjects = new ArrayList<Gui>();
	public static ArrayList<Gui> guiArray = new ArrayList<Gui>();
	
	public static Gui add(Gui gui){
		guiArray.add(gui);
		return gui;
	}
	public static void onUpdate(){
		/*
		 * Clearing visible objects on every onUpdate call
		 * to make sure objects don't randomly come up on the screen
		 */
		visibleObjects.clear();
        for (Gui aGuiArray : guiArray) {
            aGuiArray.onUpdate();

			/*
			 * Adding objects to visibleObjects array
			 */
            double x = aGuiArray.getX();
            double y = aGuiArray.getY();
            int width = aGuiArray.getWidth();
            int height = aGuiArray.getHeight();

            double sX = -Screen.translate_x;
            double sY = -Screen.translate_y;
            int sWidth = Screen.getWidth();
            int sHeight = Screen.getHeight();
			/*
			 * Checking if either 4 of the corners are on the screen
			 * if they are, they get put into the array visibleObjects,
			 * this is both for the Mouse class and rendering, makes sure
			 * you aren't rendering anything that is on current specified
			 * state but is outside of the view.
			 */
			/*
			 *  @@@____
			 *  @@@....|
			 *  |......|
			 *  |______|
			 */
            boolean one = x >= sX && x <= sX + sWidth && y >= sY && y <= sY + sHeight;
			/*
			 *  _____@@@
			 *  |....@@@
			 *  |......|
			 *  |______|
			 */
            boolean two = x + width >= sX && x + width <= sX + sWidth && y >= sY && y <= sY + sHeight;
			/*
			 *  ________
			 *  |......|
			 *  @@@....|
			 *  @@@____|
			 */
            boolean three = x >= sX && x <= sX + sWidth && y + height >= sY && y + height <= sY + sHeight;
			/*
			 *  ________
			 *  |......|
			 *  |....@@@
			 *  |____@@@
			 */
            boolean four = x + width >= sX && x + width <= sX + sWidth && y + height >= sY && y + height <= sY + sHeight;
            if (one || two || three || four) {
                visibleObjects.add(aGuiArray);
            }
        }
	}
}
