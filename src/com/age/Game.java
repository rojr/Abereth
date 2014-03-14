package com.age;

/**
 * Created by apex on 14/03/14.
 */
public class Game {
    public static View currentView;
    private View overLay;
    public Game(String title, View view, int width, int height){
        Screen.create(width,height,title);
        currentView = view;
        view.setGame(this);
        while(!Screen.isCloseRequested()){
            Screen.update();
            currentView.update();
            Screen.refresh(30);
        }
    }

    public boolean changeView(View view){
        currentView.dispose();
        view.setGame(this);
        this.currentView = view;
        return true;
    }
}
