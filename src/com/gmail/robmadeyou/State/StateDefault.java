package com.gmail.robmadeyou.State;

public class StateDefault implements State {
	
	private String name;
	public StateDefault(String name){
		this.name = name;
	}
	public String name() {
		return name;
	}

}
