package com.age.astar;

import com.age.logic.entity.Entity;
import com.age.world.Tile;
import com.age.world.World;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Mrgadgetz
 * Date: 8/21/13
 * Time: 7:25 PM
 */
public class AStarSearch {

    private World map;
    private Heuristic heuristic;

    public AStarSearch(){
        this(World.activeWorld, new DijkstraHeuristic());
    }

    public AStarSearch(World map, Heuristic heuristic) {
        this.map = map;
        this.heuristic = heuristic;
    }

    public ArrayList<Tile> search(Entity e, Tile end){
        return search(map.get((int)((e.getDrawX() + e.getFinalDrawWidth() / 2) / map.getDimensions()),(int) ((e.getDrawY() - e.getDrawHeight() / 2) / map.getDimensions())), end);
    }
    public ArrayList<Tile> search(Tile start, Tile end) {
        int MAP_WIDTH = map.getWorldWidth();
        int MAP_HEIGHT = map.getWorldHeight();


        for (int x = 0; x < MAP_WIDTH; ++x) {
            for (int y = 0; y < MAP_HEIGHT; ++y) {
                double val1 = Math.abs(x - start.getDrawX());
                double val2 = Math.abs(y - start.getDrawY());
                try{
                    map.get(x, y).setG_score(val1 + val2);
                    map.get(x, y).setH_score(heuristic.calculate(map.get(x, y), end));
                }catch (ArrayIndexOutOfBoundsException e){
                    e.printStackTrace();
                    //TODO make a self fixing mechanism to stop this bad boy from throwing infinite errors
                }
            }
        }

        PriorityQueue<Tile> openSet = new PriorityQueue<Tile>();

        openSet.offer(start);
        HashSet<Tile> closedSet = new HashSet<Tile>();

        while (openSet.size() > 0 && openSet.peek() != end) {

            Tile current = openSet.poll();

            current.consider();
            closedSet.add(current);

            ArrayList<Tile> neighbors = new ArrayList<Tile>();

            if (current.getDrawY() - 1 >= 0 && !map.get((int)current.getDrawX(), (int)current.getDrawY() - 1, true).isSolid())
                neighbors.add(map.get((int)current.getDrawX(), (int)current.getDrawY() - 1, true));

            if (current.getDrawY() + 1 < MAP_HEIGHT && !map.get((int)current.getDrawX(), (int)current.getDrawY() + 1, true).isSolid())
                neighbors.add(map.get((int)current.getDrawX(), (int)current.getDrawY() + 1, true));

            if (current.getDrawX() - 1 >= 0 && !map.get((int) current.getDrawX() - 1, (int) current.getDrawY(), true).isSolid())
                neighbors.add(map.get((int) current.getDrawX() - 1, (int) current.getDrawY(), true));

            if (current.getDrawX() + 1 < MAP_WIDTH && !map.get((int) current.getDrawX() + 1, (int) current.getDrawY(), true).isSolid())
                neighbors.add(map.get((int) current.getDrawX() + 1, (int) current.getDrawY(), true));

            for (Tile neighbor : neighbors) {
                double cost = current.getG_score() + movement_cost(current, neighbor);

                if (openSet.contains(neighbor) && cost <= neighbor.getG_score())
                    openSet.remove(neighbor);

                if (closedSet.contains(neighbor) && cost <= neighbor.getG_score())
                    closedSet.remove(neighbor);

                if (!openSet.contains(neighbor) && !closedSet.contains(neighbor)) {
                    neighbor.setG_score(cost);
                    openSet.add(neighbor);
                    neighbor.setParent(current);
                }
            }
        }

        return reconstruct_path(end, start);
    }

    private ArrayList<Tile> reconstruct_path(Tile current, Tile start) {
        ArrayList<Tile> path = new ArrayList<Tile>();
        if (current == null)
            return path;
        if (current.getParent() != start) {
            path.addAll(reconstruct_path(current.getParent(), start));
        }
        path.add(current);
        return path;
    }

    private int movement_cost(Tile current, Tile neighbor) {
        return 1;
    }
}