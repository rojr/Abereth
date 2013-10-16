package com.gmail.robmadeyou.state;

public class StateDefault implements ABState {

    private String name;

    public StateDefault(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

}
