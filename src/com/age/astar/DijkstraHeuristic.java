package com.age.astar;

import com.age.world.Tile;

/**
 * Created by apex on 28/02/14.
 */
public class DijkstraHeuristic implements Heuristic{

    @Override
    public double calculate(Tile start, Tile end){
        return 0.0;
    }

    @Override
    public String getName(){
        return "Dijkstra";
    }
}
