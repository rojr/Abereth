package com.age.astar;

import com.age.world.Tile;

/**
 * Created by apex on 28/02/14.
 */
public class ManhattenHeuristic implements Heuristic{

    private int d = 1;

    @Override
    public double calculate(Tile start, Tile end){
        return (d * ((Math.abs(start.getDrawX() - end.getDrawX()) + (Math.abs(start.getDrawY() - end.getDrawY())))));
    }

    @Override
    public String getName(){
        return "Manhattan";
    }
}
