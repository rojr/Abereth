package com.gmail.robmadeyou;

import com.gmail.robmadeyou.Entity.ABEntity;
import com.gmail.robmadeyou.Entity.ABNpc;
import com.gmail.robmadeyou.Entity.ABPlayer;
import com.gmail.robmadeyou.Object.ABItem;

public class ABTarget {

	ABEntity player;
	ABItem item;
	String from = "";
	public ABTarget(ABEntity player){
		this.player = player;
		from = "p";
	}
	public ABTarget(ABItem item){
		this.item = item;
		from = "i";
	}
	public float getX(){
		if(from.equals("p")){
			return player.getX();
		}else{
			return item.getX();
		}
	}
	public float getY(){
		if(from.equals("p")){
			return player.getY();
		}else{
			return item.getY();
		}
	}
	public float getWidth(){
		if(from.equals("p")){
			return player.getWidth();
		}else{
			return item.getWidth();
		}
	}
	public float getHeight(){
		if(from.equals("p")){
			return player.getHeight();
		}else{
			return item.getHeight();
		}
	}
	public float getSpeed(){
		if(from.equals("p")){
			return player.getSpeed();
		}else{
			return 4;
		}
	}
}
