package com.gmail.robmadeyou.Gui;

import com.gmail.robmadeyou.Draw.ABCollector;
import com.gmail.robmadeyou.Draw.ABCollector.DrawParameters;
import com.gmail.robmadeyou.Effects.ABColor;
import com.gmail.robmadeyou.Effects.ABTextDraw;
import com.gmail.robmadeyou.Input.ABKeyboard;
import com.gmail.robmadeyou.Input.ABKeyboard.ABKey;
import com.gmail.robmadeyou.ABLayer;

public class ABMessageArea {
    static ABColor color = ABColor.Brick;
    public static float opacity = 1f;
    private static int x = 0, y = 0, width = 0, height = 0;
    private static int tX = 0, tY = 0;
    static String currentMessage = "oh  ";
    static String messageList[];
    static int messageIndex = 0;
    static ABTextDraw messageArea = new ABTextDraw(currentMessage, x, y, ABColor.Red);

    public static void setUp(int x2, int y2, int width2, int height2) {
        x = x2;
        y = y2;
        width = width2;
        height = height2;
        tX = x2;
        tY = y2;
        messageArea = new ABTextDraw(currentMessage, x, y, color);
    }

    public static void setUpTextStart(int x, int y) {
        tX = x;
        tY = y;
    }

    public static void addListOfMessages(String list[]) {
        messageList = list;
        messageIndex = 0;
        messageArea = new ABTextDraw(messageList[messageIndex], tX, tY, color);
    }

    private static void nextMessage() {
        if (messageList[messageIndex] != null) {
            messageIndex++;
            messageArea = new ABTextDraw(messageList[messageIndex], tX, tY, color);
        }
    }

    public static void onUpdate() {
    	DrawParameters p = new DrawParameters("box", x, y, width, height);
    		p.setTextureID(-1);
    		p.setColor(color);
    		p.setOpacity(opacity);
    		p.setLayer(ABLayer.GUILayer());
        ABCollector.add(p);
        if (ABKeyboard.isKeyPressed(ABKey.Return)) {
            if (messageList.length > messageIndex + 1) {
                nextMessage();
            }
        }
        messageArea.onUpdate();
    }
}
