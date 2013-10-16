package com.gmail.robmadeyou.gui;

import com.gmail.robmadeyou.ABScreen;

import java.util.ArrayList;
/**
 * ABUI stands for Abereth User Interface. It literally holds all of the
 * Gui objects visible to the game and does fun stuff with them
 * @author Apex
 *
 */
public class ABUI {

    public static ArrayList<ABGui> visibleObjects = new ArrayList<ABGui>();
    public static ArrayList<ABGui> guiArray = new ArrayList<ABGui>();

    public static ABGui add(ABGui gui) {
        guiArray.add(gui);
        return gui;
    }

    public static void onUpdate() {
        /*
		 * Clearing visible objects on every onUpdate call
		 * to make sure objects don't randomly come up on the screen
		 */
        visibleObjects.clear();
        for (ABGui aGuiArray : guiArray) {
            aGuiArray.update();

			/*
			 * Adding objects to visibleObjects array
			 */
            float x = aGuiArray.getX();
            float y = aGuiArray.getY();
            float width = aGuiArray.getWidth();
            float height = aGuiArray.getHeight();

            double sX = 0;
            double sY = 0;
            int sWidth = ABScreen.getWidth();
            int sHeight = ABScreen.getHeight();
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
            if (one || two || three || four || aGuiArray.getUseTranslate()) {
                visibleObjects.add(aGuiArray);
            }
        }
    }
}
