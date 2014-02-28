package com.age.world;

import com.age.graphics.render.shapes.Box;

public class Tile extends Box implements Comparable{

    private TileType type;

    private double g_score, h_score;
    private Tile parent;
    private boolean isConsidered, isVisited;
    public Tile(TileType type, double x, double y, double width, double height){
        super(x,y,width,height);
        this.type = type;
        setTexture(type.getTexture());
        setUseTranslate(true);
        toEngine();
        g_score = Double.MAX_VALUE;
        h_score = g_score;
        parent = null;
        isVisited = false;
    }
    public boolean isSolid(){
        return type.isSolid();
    }

    public void setG_score(double score){
        this.g_score = score;
    }

    public void setH_score(double score){
        this.h_score = score;
    }

    public void setParent(Tile parent){
        this.parent = parent;
    }

    public void setConsidered(boolean considered){
        this.isConsidered = considered;
    }

    public void setVisited(boolean visited){
        this.isVisited = visited;
    }

    public double getG_score(){
        return g_score;
    }

    public Tile getParent(){
        return parent;
    }

    public boolean isConsidered(){
        return isConsidered;
    }

    public boolean isVisited(){
        return isConsidered;
    }

    public void consider(){
        this.isConsidered = true;
    }
    public double getH_score(){
        return  h_score;
    }
    public void setType(TileType type){
        this.type = type;
        setTexture(type.getTexture());
    }
    public TileType getType(){
        return type;
    }

    @Override
    public int compareTo(Object t){
        Tile g = (Tile) t;
        //My brain is dry, can't think of variable names
        if(g_score + h_score < g.getG_score() + g.getH_score()) return -1;
        if(g_score + h_score > g.getG_score() + g.getH_score()) return 1;
        return 0;
    }

    @Override
    public String toString(){
        return getType().getName() + ": x - " + getDrawX() + " y - " + getFinalDrawY();
    }
}
