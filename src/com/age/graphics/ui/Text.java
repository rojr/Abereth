package com.age.graphics.ui;

import com.age.exceptions.InvalidObjectType;
import com.age.helper.Child;


/**
 * Created by apex on 10/03/14.
 */
public class Text extends Container {

    private double lastX, lastY;
    private int letterSize = 8;
    private String text;
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
        if((text.length() > 0)){
            for(char c : text.toCharArray()){
                if(c == ' ')
                    lastX += letterSize;
                else if(c != '\n')
                    add(new Letter(c,0,0,letterSize,letterSize));
                else{
                    lastX = getDrawX();
                    lastY += letterSize;
                }
            }
        }
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
            for(char c : text.toCharArray()){
                if(c == ' ')
                    lastX += letterSize;
                else if(c != '\n')
                    add(new Letter(c,0,0,letterSize,letterSize));
                else{
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