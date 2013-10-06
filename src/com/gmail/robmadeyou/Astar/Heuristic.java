package com.gmail.robmadeyou.Astar;

import com.gmail.robmadeyou.Block.ABBlock;

/**
 * Created by Mrgadgetz
 * Date: 8/21/13
 * Time: 7:27 PM
 */
public interface Heuristic {
    double calculate(ABBlock start, ABBlock end);

    String getName();
}
