package com.gmail.robmadeyou.Gui;

import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Draw.Collector.DrawParameters;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.TextDraw;
import com.gmail.robmadeyou.Input.Keyboard;
import com.gmail.robmadeyou.Input.Keyboard.Key;
import com.gmail.robmadeyou.Layer;

public class MessageArea {
    static Color color = Color.Brick;
    public static float opacity = 1f;
    private static int x = 0, y = 0, width = 0, height = 0;
    private static int tX = 0, tY = 0;
    static String currentMessage = "oh  ";
    static String messageList[];
    static int messageIndex = 0;
    static TextDraw messageArea = new TextDraw(currentMessage, x, y, Color.Red);

    public static void setUp(int x2, int y2, int width2, int height2) {
        x = x2;
        y = y2;
        width = width2;
        height = height2;
        tX = x2;
        tY = y2;
        messageArea = new TextDraw(currentMessage, x, y, color);
    }

    public static void setUpTextStart(int x, int y) {
        tX = x;
        tY = y;
    }

    public static void addListOfMessages(String list[]) {
        messageList = list;
        messageIndex = 0;
        messageArea = new TextDraw(messageList[messageIndex], tX, tY, color);
    }

    private static void nextMessage() {
        if (messageList[messageIndex] != null) {
            messageIndex++;
            messageArea = new TextDraw(messageList[messageIndex], tX, tY, color);
        }
    }

    public static void onUpdate() {
        Collector.add(new DrawParameters("box", x, y, width, height, -1, Color.Blue, opacity, Layer.GUILayer(), false, false));
        if (Keyboard.isKeyPressed(Key.Return)) {
            if (messageList.length > messageIndex + 1) {
                nextMessage();
            }
        }
        messageArea.onUpdate();
    }
}
