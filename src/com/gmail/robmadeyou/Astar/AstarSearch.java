package com.gmail.robmadeyou.Astar;

import com.gmail.robmadeyou.Block.ABBlock;
import com.gmail.robmadeyou.World.ABBlockMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Mrgadgetz
 * Date: 8/21/13
 * Time: 7:25 PM
 */
public class AstarSearch {

    private ABBlockMap map;
    private Heuristic heuristic;

    public AstarSearch(ABBlockMap map, Heuristic heuristic) {

        this.map = map;
        this.heuristic = heuristic;
    }

    public ArrayList<ABBlock> search(ABBlock start, ABBlock end) {
        int MAP_WIDTH = map.getLength();
        int MAP_HEIGHT = map.getMapHeight();

        
        for (int x = 0; x < MAP_WIDTH; ++x) {
            for (int y = 0; y < MAP_HEIGHT; ++y) {
                double val1 = Math.abs(x - start.getX());
                double val2 = Math.abs(y - start.getY());
                map.getBlock(x, y).setG_Score(val1 + val2);
                map.getBlock(x, y).setHscore(heuristic.calculate(map.getBlock(x, y), end));
            }
        }

        PriorityQueue<ABBlock> openSet = new PriorityQueue<ABBlock>();

        openSet.offer(start);
        HashSet<ABBlock> closedSet = new HashSet<ABBlock>();

        while (openSet.size() > 0 && openSet.peek() != end) {

            ABBlock current = openSet.poll();

            current.consider();
            closedSet.add(current);

            ArrayList<ABBlock> neighbors = new ArrayList<ABBlock>();

            if (current.getY() - 1 >= 0 && !map.getBlock(current.getX(), current.getY() - 1).isSolid())
                neighbors.add(map.getBlock(current.getX(), current.getY() - 1));

            if (current.getY() + 1 < MAP_HEIGHT && !map.getBlock(current.getX(), current.getY() + 1).isSolid())
                neighbors.add(map.getBlock(current.getX(), current.getY() + 1));

            if (current.getX() - 1 >= 0 && !map.getBlock(current.getX() - 1, current.getY()).isSolid())
                neighbors.add(map.getBlock(current.getX() - 1, current.getY()));

            if (current.getX() + 1 < MAP_WIDTH && !map.getBlock(current.getX() + 1, current.getY()).isSolid())
                neighbors.add(map.getBlock(current.getX() + 1, current.getY()));

            for (ABBlock neighbor : neighbors) {
                double cost = current.getG_Score() + movement_cost(current, neighbor);

                if (openSet.contains(neighbor) && cost <= neighbor.getG_Score())
                    openSet.remove(neighbor);

                if (closedSet.contains(neighbor) && cost <= neighbor.getG_Score())
                    closedSet.remove(neighbor);

                if (!openSet.contains(neighbor) && !closedSet.contains(neighbor)) {
                    neighbor.setG_Score(cost);
                    openSet.add(neighbor);
                    neighbor.setParent(current);
                }
            }
        }

        return reconstruct_path(end, start);
    }

    private ArrayList<ABBlock> reconstruct_path(ABBlock current, ABBlock start) {
        ArrayList<ABBlock> path = new ArrayList<ABBlock>();
        if (current == null)
            return path;
        if (current.getParent() != start) {
            path.addAll(reconstruct_path(current.getParent(), start));
        }
        path.add(current);
        return path;
    }

    private int movement_cost(ABBlock current, ABBlock neighbor) {
        return 1;
    }
}
