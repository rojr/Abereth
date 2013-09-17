package com.gmail.robmadeyou.Effects;

import com.gmail.robmadeyou.Gui.Text;
import com.gmail.robmadeyou.Layer;

public class TextDraw {
    private int numOfCharacters, currentCharacter = 1;
    private double x, y;
    private String text, convertexText[], lastText = "";
    private Color color;

    public TextDraw(String text, double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.numOfCharacters = text.length();
        this.color = color;
    }

    public void onUpdate() {
        if (numOfCharacters > currentCharacter) {
            convertexText = text.split(text.substring(currentCharacter));
            currentCharacter++;
            Text.drawString(convertexText[0], (float) x, y, Layer.GUILayer(), 1, 1, color, false, false);
            if (numOfCharacters == currentCharacter) {
                lastText = convertexText[0];
                try {
                    lastText = text;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            Text.drawString(lastText,(float) x, y, Layer.GUILayer(), 1, 1, color, false, false);
        }

    }
}
