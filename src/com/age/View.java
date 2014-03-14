package com.age;

import com.age.graphics.Drawable;

import java.util.ArrayList;

public abstract class View {
	String name;
    private Game game;
    ArrayList<Drawable> drawList = new ArrayList<Drawable>();
	public View(String name){
		this.name = name;
	}

    public Game getGame(){
        return game;
    }

    public void setGame(Game game){
        this.game = game;
    }
	public String getName(){
		return name;
    }

    public void add(Drawable d){
        drawList.add(d);
    }

    /**
     * Do not call this command, it will render every object again and cause an fps drop
     */
    private void render(){
        for(Drawable d : drawList){
            d.render();
        }
    }
	public abstract void update();
	public abstract void dispose();

}
