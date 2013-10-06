package com.gmail.robmadeyou.Astar;

import com.gmail.robmadeyou.Block.ABBlock;

/**
 * Created by Mrgadgetz
 * Date: 8/29/13
 * Time: 7:53 PM
 */
public class DijkstraHeuristic implements Heuristic {
    @Override
    public double calculate(ABBlock start, ABBlock end) {
        return 0.0;
    }

    @Override
    public String getName() {
        return "Dijkstra";
    }
}
