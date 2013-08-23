package com.gmail.robmadeyou.Astar;

import com.gmail.robmadeyou.Block.Block;

/**
 * Created by Mrgadgetz
 * Date: 8/21/13
 * Time: 7:30 PM
 */
public class EuclideanHeuristic implements Heuristic {
    @Override
    public double calculate(Block start, Block end) {
        return Math.sqrt(Math.pow(end.getY() - start.getY(), 2) + Math.pow(end.getX() - start.getX(), 2));

    }

    @Override
    public String getName() {
        return "Euclidean";
    }
}
