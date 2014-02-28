package com.age.astar;

import com.age.world.Tile;
/**
 * Created by apex on 28/02/14.
 */
public interface Heuristic {
    double calculate(Tile start, Tile end);
    String getName();
}
