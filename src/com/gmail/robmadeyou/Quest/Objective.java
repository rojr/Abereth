package com.gmail.robmadeyou.quest;

import com.gmail.robmadeyou.entity.ABNpc;

public class Objective {

    private ObjectiveType type;
    private ABNpc npc;
    private boolean complete = false;

    public Objective(ObjectiveType type, ABNpc npc) {
        this.type = type;
        this.npc = npc;
    }

    public ABNpc getNpc() {
        return npc;
    }

    public void complete() {
        this.complete = true;
    }

    public boolean isComplete() {
        return complete;
    }
}
