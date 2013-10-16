package com.gmail.robmadeyou.astar;

import com.gmail.robmadeyou.block.ABBlock;

/**
 * Created by Mrgadgetz
 * Date: 8/22/13
 * Time: 1:16 AM
 */
public class ManhattenHeuristic implements Heuristic {

    private int d = 1;

    @Override
    public double calculate(ABBlock start, ABBlock end) {

        return (d * ((Math.abs(start.getX() - end.getX()) + (Math.abs(start.getY() - end.getY())))));
    }

    @Override
    public String getName() {
        return "Manhattan";
    }
}
