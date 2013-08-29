package com.gmail.robmadeyou.Quest;

import com.gmail.robmadeyou.Entity.Npc;

public class Objective {

    private ObjectiveType type;
    private Npc npc;
    private boolean complete = false;

    public Objective(ObjectiveType type, Npc npc) {
        this.type = type;
        this.npc = npc;
    }

    public Npc getNpc() {
        return npc;
    }

    public void complete() {
        this.complete = true;
    }

    public boolean isComplete() {
        return complete;
    }
}
