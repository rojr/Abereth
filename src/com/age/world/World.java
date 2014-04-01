package com.age.world;

import com.age.Screen;
import com.age.event.Event;

import java.io.*;

/**
 * Created by apex on 03/02/14.
 */
public class World {
    /**
     * Currently active world that the user will see and
     * be able to play on.
     */
    public static World activeWorld;

    private Event event;

    public static int TILE_DIMENSIONS(){
        return activeWorld.getDimensions();
    }
    
    private Tile[][] mapList;
    private int TILE_SIZE;

    /**
     * Creates a new world with enough tiles to cover the whole screen
     * Sets the default tile size to 32.
     */
    public World(){
        this(32, Screen.getWidth() / 32, Screen.getHeight() / 32);
    }

    /**
     * Creates a new world
     * @param tileSize Size of each tile
     * @param x number of tiles horizontally
     * @param y number of tiles vertically
     */
    public World(int tileSize, int x, int y){
        this.TILE_SIZE = tileSize;
        mapList = new Tile[x][y];
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                if(j == y - 1 || j == y - 2)
                    mapList[i][j] = new Tile(TileType.BRICK,i * tileSize,j * tileSize,tileSize,tileSize);
                else {
                    mapList[i][j] = new Tile(TileType.AIR, i * tileSize, j * tileSize, tileSize, tileSize);
                }
            }
        }
    }

    public boolean isSolidAt(int x, int y){
        if(x > mapList.length || y > mapList.length) throw new ArrayIndexOutOfBoundsException();
        return mapList[x][y].isSolid();
    }

    public void set(TileType type,int x,int y){
        if(x > mapList.length || y > mapList.length) throw new ArrayIndexOutOfBoundsException();
        mapList[x][y].setType(type);
    }

    /**
     * @param x
     * @param y
     * @return a single tile at specific x or y
     * @throws java.lang.ArrayIndexOutOfBoundsException
     */
    public Tile get(int x, int y){
        if(x > mapList.length || y > mapList[0].length || x < 0 || y < 0) throw new ArrayIndexOutOfBoundsException();
        return mapList[x][y];
    }

    /**
     * Gets the tile at x, y on the world, rather than
     * getting the Tile[x][y].
     * @param x
     * @param y
     * @param divide If the numbers should be divided by the tile_size or not
     * @return Returns Tile[x/TILE_SIZE][y/TILE_SIZE]
     */
    public Tile get(int x, int y, boolean divide){
        if(divide){
            x /= TILE_DIMENSIONS();
            y /= TILE_DIMENSIONS();
            return get(x,y);
        }else{
            return get(x,y);
        }
    }

    /**
     * @return Whole array containing the world with all the tiles
     */
    public Tile[][] get(){
        return mapList;
    }


    public int getDimensions(){
        return TILE_SIZE;
    }
    public int getWorldWidth() {
        return mapList.length;
    }

    public int getWorldHeight(){
        return mapList[0].length;
    }

    public Tile[][] getList(){
        return mapList;
    }
    
    public void load(){
        try{
            FileReader reader = new FileReader("save.txt");
            BufferedReader br = new BufferedReader(reader);
            for(int i = 0; i < mapList.length; i++){
                for(int j = 0; j < mapList[i].length; j++){
                    if(mapList[i][j] != null){
                        String line = br.readLine();
                        //TODO create a smart way to load all tiles, instead of hard coding them all
                        if(line.equalsIgnoreCase("air")){
                            mapList[i][j].setType(TileType.AIR);
                        }else if(line.equalsIgnoreCase("brick")){
                            mapList[i][j].setType(TileType.BRICK);
                        }
                    }
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addEvent(Event event){
        this.event = event;
    }

    /**
     * Saves the map to a specific file
     * @param filename
     */
    public void save(String filename){
        //TODO Add checks if world exists
        //Also add overriding of world or making copies to avoid overriding.
        try{
            FileWriter writer = new FileWriter(filename, false);
            BufferedWriter bw = new BufferedWriter(writer);
            for(int i = 0; i < mapList.length; i++){
                for(int j = 0; j < mapList[i].length; j++){
                    bw.write(mapList[i][j].getType().getName());
                    bw.newLine();
                }
            }
            bw.flush();
            bw  .close();
        }catch(Exception e){e.printStackTrace();}
    }

    public static void load(World world){
        activeWorld = world;
    }

    public void onUpdate(){
        //event.event(this);
    }
}
