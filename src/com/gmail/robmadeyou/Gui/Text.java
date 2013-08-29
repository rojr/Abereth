package com.gmail.robmadeyou.Gui;

import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Draw.Collector.DrawParameters;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.TextureLoader;

import java.util.ArrayList;

public class Text {

    private static char letters[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private static ArrayList<Integer> letterTexID = new ArrayList<Integer>();

    private static char numbers[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static ArrayList<Integer> numberTexID = new ArrayList<Integer>();

    public static void loadTextures() {
        for (int i = 0; i < letters.length / 2; i++) {
            letterTexID.add(TextureLoader.createTexture("res/font/sprite1.png", i * 8, 0, 8, 8));
        }
        for (int i = 0; i < letters.length / 2; i++) {
            letterTexID.add(TextureLoader.createTexture("res/font/sprite1.png", i * 8, 8, 8, 8));
        }
        for (int i = 0; i < numbers.length; i++) {
            numberTexID.add(TextureLoader.createTexture("res/font/sprite1.png", i * 8, 16, 8, 8));
        }
    }

    public static void drawString(String text, double x, double y, int layerID, float opacity, double size, Color color, boolean useTranslate, boolean inverts) {
        double originalX = x;
        for (char c : text.toCharArray()) {
            for (int i = 0; i < letters.length; i++) {
                if (c == letters[i]) {
                    if (c == 'g' || c == 'q' || c == 'p' || c == 'j' || c == 'y') {
                        y += 1;
                    }
                    Collector.add(new DrawParameters("box", x, y, 8, 8, letterTexID.get(i), color, opacity, layerID, useTranslate, inverts));
                    if (c == 'g' || c == 'q' || c == 'p' || c == 'j' || c == 'y') {
                        y -= 1;
                    }
                    if (c == 'f' || c == 'l' || c == 't') {
                        x += 7;
                    } else if (c == 'i') {
                        x += 5;
                    } else {
                        x += 8;
                    }
                }
            }
            if (c == ' ') {
                x += 8;
            } else if (c == '\n') {
                x = originalX;
                y += 9;
            }
            for (int i = 0; i < numbers.length; i++) {
                if (c == numbers[i]) {
                    Collector.add(new DrawParameters("box", x, y, 8, 8, numberTexID.get(i), color, opacity, layerID, useTranslate, inverts));
                    x += 8;
                }
            }
        }
    }

}
