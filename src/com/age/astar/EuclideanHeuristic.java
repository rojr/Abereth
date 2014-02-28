package com.age.astar;

import com.age.world.Tile;

/**
 * Created by apex on 28/02/14.
 */
public class EuclideanHeuristic implements Heuristic{
    @Override
    public double calculate(Tile start, Tile end){
        return Math.sqrt(Math.pow(end.getDrawY() - start.getDrawY(), 2) + Math.pow(end.getDrawX() - start.getDrawX(), 2));
    }

    @Override
    public String getName(){
        return "Euclidean";
    }
}
