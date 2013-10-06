package com.gmail.robmadeyou;

import java.util.ArrayList;

import com.gmail.robmadeyou.Draw.ABCollector;
import com.gmail.robmadeyou.Draw.ABCollector.DrawParameters;

public class ABLayer implements Comparable {
    public static int layers = 1;

    public static int topLayer() {
        return layers - 1;
    }

    public static int GUILayer() {
        return layers;
    }

    public static void addLayer() {
        layers++;
    }

    public static void addLayer(int amount) {
        
    	//layers += amount;
    }

    public static void onUpdate() {

    }
    
    
    private ArrayList<ABCollector.DrawParameters> drawList = new ArrayList<ABCollector.DrawParameters>();
    private int number;
    public ABLayer(int number){
    	this.number = number;
    }
    public int getNumber(){
    	return number;
    }
    public void setNumber(int number){
    	this.number = number;
    }
    public void add(DrawParameters p){
    	drawList.add(p);
    }
    
    public ArrayList<DrawParameters> getList(){
    	return drawList;
    }
    public void clear(){
    	drawList.clear();
    }

	public int compareTo(Object l) {
		int otherLayer = ((ABLayer) l).getNumber();
		return this.number - otherLayer;
	}
}
