package com.gmail.robmadeyou.Astar;

import com.gmail.robmadeyou.Block.Block;

/**
 * Created by Mrgadgetz
 * Date: 8/21/13
 * Time: 7:27 PM
 */
public interface Heuristic {
    double calculate(Block start, Block end);

    String getName();
}
