package com.age.logic.entity;

import com.age.Age;
import com.age.tests.KeepOnDying;
import com.age.world.Physics;
import com.age.graphics.render.shapes.Box;
import com.age.logic.Living;
import com.age.astar.*;

import com.age.world.Tile;
import com.age.world.World;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;


/**
 * Created by apex on 03/02/14.
 */
public class Entity extends Box implements Physics, Living {

    private final int DIRECTION_LEFT = 0;
    private final int DIRECTION_RIGHT = 1;
    private final int DIRECTION_UP = 2;
    private final int DIRECTION_DOWN = 3;

    private final int DIRECTION_NORTH = 2;
    private final int DIRECTION_EAST = 1;
    private final int DIRECTION_SOUTH = 3;
    private final int DIRECTION_WEST = 0;

    private final int DIRECTION_NORTH_EAST = 4;
    private final int DIRECTION_NORTH_WEST = 5;

    private final int DIRECTION_SOUTH_EAST = 6;
    private final int DIRECTION_SOUTH_WEST = 7;

    private float gravity = 0.2f;
    private float gravityMultiplier = 1;
    private Vector2f velocity = new Vector2f();
    private float speed = 1f;
    private float originalSpeed = speed;
    private boolean isJumping = false, isAirborne = false;
    private int direction = 0;
    public Entity(double x, double y, double width, double height){
        super(x,y,width, height);
        setTexture(Age.EmptyTexture);
    }

    @Override
    public Vector3f getBottom() {
        return new Vector3f((float)getDrawX(), (float)(getDrawY() + getDrawHeight()),(float)getDrawWidth());
    }

    @Override
    public Vector3f getTop() {
        return new Vector3f((float)getDrawX(), (float)(getDrawY()),(float)getDrawWidth());
    }

    @Override
    public Vector3f getLeft() {
        return new Vector3f((float)getDrawX(), (float)(getDrawY()),(float)getDrawHeight());
    }

    @Override
    public Vector3f getRight() {
        return new Vector3f((float)(getDrawX() + getDrawWidth()), (float)(getDrawY()),(float)getDrawHeight());
    }

    public Vector2f getVelocity(){
        return velocity;
    }

    public void setVelocity(Vector2f vec){
        this.velocity = vec;
    }
    public void setVelocity(float x, float y){ setVelocity(new Vector2f(x,y));};
    public void setVelocityX(float x){this.velocity.setX(x);};
    public void setVelocityY(float y){this.velocity.setY(y);};


    @Override
    public float getGravity() {
        return gravity;
    }

    /**
     * Takes the bottom of the entity to check if there
     * is anything solid below it, if there then don't fall, if not
     * then entity will just assume it's in air and fall down.
     * @return if solid underneath returns true
     */
    public boolean isSolidUnder(){
        try{
            double times = timesTileGoesInEntity(getDrawWidth());
            if(velocity.getY() >= 0){
                for(int i = 0; i < times + 1; i++){
                    //This is done because i can be 0, and you don't want to divide by zero; kids ;)
                    double plus = i == 0 ? 0 : (getDrawWidth() - 2) / i;
                    if(World.activeWorld.getList()[Math.round((int)(getBottom().getX() + plus) / World.TILE_DIMENSIONS())][Math.round((getBottom().getY() - World.TILE_DIMENSIONS() / 2) / World.TILE_DIMENSIONS())].isSolid()){
                        return true;
                    }
                }
            }
        }catch(Exception e){e.printStackTrace();}
        return false;
    }

    public boolean isSolidAbove(){
        try{
            double times = timesTileGoesInEntity(getDrawWidth());
            for(int i = 0; i < times + 1; i++){
                //This is done because i can be 0, and you don't want to divide by zero; kids ;)
                double plus = i == 0 ? 0 : (getDrawWidth() - 2) / i;
                if(World.activeWorld.getList()[Math.round((int)(getTop().getX() + plus) / World.TILE_DIMENSIONS())][Math.round((getTop().getY()) / World.TILE_DIMENSIONS())].isSolid()){
                    return true;

                }
            }
        }catch(Exception e){e.printStackTrace();}
        return false;
    }

    public boolean isSolidLeft(){
        try{
            double times = timesTileGoesInEntity(getDrawHeight());
            for(int i = 0; i < times; i++){
                //This is done because i can be 0, and you don't want to divide by zero; kids ;)
                /*
                 * UPDATE
                 * This line below was changed so many times, and now, it's finally purrfect (for now)
                 */
                double plus = i == 0 ? 2 : ((getDrawHeight() - 2) / times) * i;
                Tile tile = World.activeWorld.getList()[Math.round((getLeft().getX() + World.TILE_DIMENSIONS() / 2 - 2) / World.TILE_DIMENSIONS()) - 1][Math.round((int)(getLeft().getY() + plus) / World.TILE_DIMENSIONS())];
                if(tile.isSolid()){
                    return true;
                }
            }
        }catch(Exception e){e.printStackTrace();}
        return false;
    }

    public boolean isSolidRight(){
        try{
            double times = timesTileGoesInEntity(getDrawHeight());
            for(int i = 0; i < times ; i++){
                //This is done because i can be 0, and you don't want to divide by zero; kids ;)
                /*
                 * UPDATE
                 * This line below was changed so many times, and now, it's finally purrfect (for now)
                 */
                double plus = i == 0 ? 2 : ((getDrawHeight() - 2) / times) * i;
                if(World.activeWorld.getList()[Math.round((int)(getRight().getX()) / World.TILE_DIMENSIONS())][Math.round(((int)(getRight().getY() + plus) / World.TILE_DIMENSIONS()))].isSolid()){
                    return true;
                }
            }
        }catch(Exception e){e.printStackTrace();}
        return false;
    }

    private double timesTileGoesInEntity(double size){
        return size / (double) World.TILE_DIMENSIONS();
    }

    public boolean moveLeft(){
        velocity.setX(-speed);
        if(isSolidLeft()){
            return true;
        }
        return false;
    }
    public boolean moveRight(){
        velocity.setX(speed);
        if(isSolidRight()){
            return true;
        }
        return false;
    }

    public float getSpeed(){
        return speed;
    }
    public float getDirection() {return direction;}

    /**
     * Sets the entity speed and the original speed of the entity,
     * making it a fixed speed that will not be different from
     * the original.
     * @param speed
     */
    public void setSpeed(float speed){
        this.speed = speed;
        this.originalSpeed = speed;
    }

    /**
     * Sets temporary speed, this will be different to the Original speed
     * that the entity will finally revert back to.
     * @param speed
     */
    public void setTempSpeed(float speed){
        this.speed = speed;
    }

    @Override
    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public void update(double delta){
        isAirborne = !isSolidUnder() ? true : false;

        if(isAirborne){
            if(gravityMultiplier < 10){
                gravityMultiplier *= 1.00000001;
            }
            if(velocity.getY() < 0){
                velocity.setY(velocity.getY() + getGravity() * gravityMultiplier);
                if(isSolidAbove() && !isSolidUnder()){
                    velocity.setY(0);
                    setDrawY(World.activeWorld.getList()[Math.round(getBottom().getX() / World.TILE_DIMENSIONS())][Math.round(getTop().getY() / World.TILE_DIMENSIONS())].getDrawY() +
                            World.activeWorld.getList()[Math.round(getBottom().getX() / World.TILE_DIMENSIONS())][Math.round(getTop().getY() / World.TILE_DIMENSIONS())].getDrawHeight());
                }
            }else{
                velocity.setY(velocity.getY() + getGravity() * gravityMultiplier);
            }
        }else{
            isJumping = false;
            if(velocity.getY() > 0){
                velocity.setY(0);
                //setDrawY(Screen.getHeight() - getDrawHeight());
            }
            gravityMultiplier = 1;
        }

        if(velocity.getX() > 0){
            if(isSolidRight()){
                velocity.setX(0);
                setDrawX(World.activeWorld.getList()[Math.round((int)(getLeft().getX() + getDrawWidth()) / World.TILE_DIMENSIONS())][Math.round(getTop().getY() / World.TILE_DIMENSIONS())].getDrawX() - getDrawWidth());
            }
        }else if(velocity.getX() < 0){
            if(isSolidLeft()){
                velocity.setX(0);
                Tile tile =  World.activeWorld.getList()[Math.round(getLeft().getX() / World.TILE_DIMENSIONS())][Math.round(getTop().getY() / World.TILE_DIMENSIONS())];
                setDrawX(tile.getDrawX());
            }
        }
        setDrawX(getDrawX() + (velocity.getX() * 0.2 * delta));
        setDrawY(getDrawY() + (velocity.getY() * 0.2 * delta));
        velocity.setX((float) (velocity.getX() * 0.8));
        if(isAirborne){
            if(isSolidUnder()){
                velocity.setY(0);
                try{
                setDrawY(World.activeWorld.getList()[Math.round(getBottom().getX() / World.TILE_DIMENSIONS())][Math.round(getBottom().getY() / World.TILE_DIMENSIONS())].getDrawY() - getDrawHeight());
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        //If entity hits anything above it, it is automatically transferred to the top of the block, rather than falling down.
        //TODO fix it now, or as soon as possible.
        while(World.activeWorld.isSolidAt((int)(getBottom().getX() + getDrawWidth() / 2) / World.TILE_DIMENSIONS(),(int)getBottom().getY() / World.TILE_DIMENSIONS() - 1)){
            setDrawY(getDrawY() - World.TILE_DIMENSIONS());
            //setDrawY(getDrawY() - 2);
        }
    }

    /**
     * Makes the entity jump
     * if <b>vl</b> is negative, entity will jump up<br>if <b>positive</b> entity will jump down.
     * @param vel
     */
    public void jump(float vel){
        if(!isAirborne && !isSolidAbove()){
            velocity.setY(vel);
            isJumping = true;
        }
    }

    /**
     * @param endpoint The tile that the entity would like be at after the end of the search
     */
    public void search(Tile endpoint){
        AStarSearch search = new AStarSearch();
        ArrayList<Tile> list = search.search(this, endpoint);

        moveTo(list.get(0));
    }

    public void moveTo(Tile to){
        moveTo(to.getDrawX() / World.TILE_DIMENSIONS(), to.getDrawY() / World.TILE_DIMENSIONS());
    }

    public void moveTo(double x, double y){
        //Current x/y in coordinate terms
        int cX =(int) getDrawX() / World.TILE_DIMENSIONS();
        int cY =(int) getDrawY() / World.TILE_DIMENSIONS();
        if(cX != x / World.TILE_DIMENSIONS() && cY != y / World.TILE_DIMENSIONS()){
            Entity e = this;
            if(this instanceof Player) e = (Player) this;
            else if(this instanceof Enemy) e = (Enemy) this;

            if(x > cX){
                e.moveRight();
            }else if(x < cX){
                e.moveLeft();
            }
            //TODO logic for jumping!

        }
    }

    @Override
    public Entity toEngine(){
        super.toEngine();
        KeepOnDying.entities.add(this);
        return this;
    }
}
