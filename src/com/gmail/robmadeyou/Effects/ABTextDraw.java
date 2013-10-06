package com.gmail.robmadeyou.Effects;

import com.gmail.robmadeyou.Gui.ABText;
import com.gmail.robmadeyou.ABLayer;

public class ABTextDraw {
    private int numOfCharacters, currentCharacter = 1;
    private float x, y;
    private String text, convertexText[], lastText = "";
    private ABColor color;

    public ABTextDraw(String text, float x, float y, ABColor color) {
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
            ABText.drawString(convertexText[0], x, y, ABLayer.GUILayer(), 1, 1, color, false, false);
            if (numOfCharacters == currentCharacter) {
                lastText = convertexText[0];
                try {
                    lastText = text;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            ABText.drawString(lastText,(float) x, y, ABLayer.GUILayer(), 1, 1, color, false, false);
        }

    }
}
