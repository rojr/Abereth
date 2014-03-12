package com.age.graphics.ui;

import com.age.exceptions.InvalidObjectType;
import com.age.graphics.effects.Color;
import com.age.helper.Child;


/**
 * Created by apex on 10/03/14.
 */
public class Text extends Container {

    private double lastX, lastY;
    private int letterSize = 8;
    private String text;
    private char identifier = '&';
    Color currentColor = Color.WHITE;
    public Text(double x, double y){
        this("", x, y);
    }
    public Text(String text, double x, double y){
        this(text, x, y, 0, 0);
    }

    public Text(String text, double x, double y, double width, double height){
        super(x,y,width,height);
        lastX = getDrawX();
        lastY = getDrawY();
        this.text = text;
        set(text);
    }

    /**
     * Sets size of the letters and spacing
     * @param size size of each letter in pixels
     */
    public void setSize(int size){
        this.letterSize = size;
        set(text);
    }

    /**
     * Default is 8
     * @return letter size
     */
    public int getSize(){
        return letterSize;
    }

    public void set(String text){
        getChildren().clear();
        lastX = getDrawX();
        lastY = getDrawY();
        this.text = text;
        if((text.length() > 0)){
            for(int i = 0; i < text.toCharArray().length; i++){
                char c = text.toCharArray()[i];
                if(c == ' ')
                    lastX += letterSize;
                else if(c != '\n'){
                    if(c == identifier && i != text.toCharArray().length -1 && i != text.toCharArray().length - 2){
                        if(text.toCharArray()[i+1] == '!'){
                            char id = text.toCharArray()[i+2];
                            switch(id){
                                case '0':
                                    currentColor = Color.WHITE;
                                    break;
                                case '1':
                                    currentColor = Color.RED;
                                    break;
                                case '2':
                                    currentColor = Color.BLUE;
                                    break;
                                case '3':
                                    currentColor = Color.YELLOW;
                                    break;
                                case '4':
                                    currentColor = Color.GREEN;
                                    break;
                                case '5':
                                    currentColor = Color.CYAN;
                                    break;
                                case '6':
                                    currentColor = Color.BLACK;
                                    break;
                                case '7':
                                    currentColor = Color.ORANGE;
                                    break;
                                case '8':
                                    currentColor = Color.PINK;
                                    break;
                                case '9':
                                    currentColor = Color.VIOLET;
                                    break;
                            }
                            i+=2;
                            continue;
                        }
                    }
                    Letter l = new Letter(c,0,0,letterSize,letterSize);
                    l.setColor(currentColor);
                    add(l);
                }else{
                    lastX = getDrawX();
                    lastY += letterSize;
                }
            }
        }
    }

    @Override
    public void add(Child c){
        if(c.getOrigin() instanceof Letter){
            c.getOrigin().setDrawX(lastX);
            char currentC = ((Letter) c.getOrigin()).getLetter();
            boolean tmp = currentC == 'p' || currentC == 'q' || currentC == 'y' || currentC == 'g' || currentC == 'j';
            double add = tmp ? letterSize / 6 : 0;
            c.getOrigin().setDrawY(lastY + add);
            if(currentC == 'i' || currentC == 'l'){
                lastX += letterSize / 2;
            }else{
                lastX += letterSize;
            }
            super.add(c);
        }else{
            try{
                System.out.println("aa");
                new InvalidObjectType("Letter").printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}