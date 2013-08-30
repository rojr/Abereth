package com.gmail.robmadeyou.Astar;

import com.gmail.robmadeyou.Block.Block;

/**
 * Created by Mrgadgetz
 * Date: 8/29/13
 * Time: 7:53 PM
 */
public class DijkstraHeuristic implements Heuristic {
    @Override
    public double calculate(Block start, Block end) {
        return 0.0;
    }

    @Override
    public String getName() {
        return "Dijkstra";
    }
}
