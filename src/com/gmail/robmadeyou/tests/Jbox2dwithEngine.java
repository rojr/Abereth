package com.gmail.robmadeyou.tests;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRectf;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glTranslatef;

import java.util.HashSet;
import java.util.Set;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import com.gmail.robmadeyou.ABScreen;
import com.gmail.robmadeyou.draw.ABBox;
import com.gmail.robmadeyou.effects.ABTextures;
import com.gmail.robmadeyou.peripherals.ABMouse;
import com.gmail.robmadeyou.world.ABCamera;
import com.gmail.robmadeyou.ABScreen.GameType;

public class Jbox2dwithEngine {
	
	private static final World world = new World(new Vec2(0, -9.8f));
	private static final Set<Body> bodies = new HashSet<Body>();
	
	public static void main(String args[]){
		ABScreen.create(600, 480, "JBox2d", GameType.SIDE_SCROLLER, false);
		
		
		
		BodyDef boxDef2 = new BodyDef();
        boxDef2.position.set(320 / 30 / 2f, 240 / 30 / 2f);
        boxDef2.type = BodyType.DYNAMIC;
        PolygonShape boxShape2 = new PolygonShape();
        boxShape2.setAsBox(0.75f, 0.75f);
        Body box2 = world.createBody(boxDef2);
        FixtureDef boxFixture2 = new FixtureDef();
        boxFixture2.density = 0.1f;
        boxFixture2.shape = boxShape2;
        box2.createFixture(boxFixture2);
        bodies.add(box2);
        
        BodyDef groundDef = new BodyDef();
        groundDef.position.set(0, 0);
        groundDef.type = BodyType.STATIC;
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(1000, 0);
        Body ground = world.createBody(groundDef);
        FixtureDef groundFixture = new FixtureDef();
        groundFixture.density = 1;
        groundFixture.restitution = 0.3f;
        groundFixture.shape = groundShape;
        ground.createFixture(groundFixture);

        BodyDef leftWallDef = new BodyDef();
        leftWallDef.position.set(0, 0);
        leftWallDef.type = BodyType.STATIC;
        PolygonShape leftWallShape = new PolygonShape();
        leftWallShape.setAsBox(0, 1000);
        Body leftWall = world.createBody(leftWallDef);
        FixtureDef leftWallFixture = new FixtureDef();
        leftWallFixture.density = 1;
        leftWallFixture.restitution = 0.3f;
        leftWallFixture.shape = leftWallShape;
        leftWall.createFixture(leftWallFixture);

        BodyDef rightWallDef = new BodyDef();
        rightWallDef.position.set(320f / 30, 0);
        rightWallDef.type = BodyType.STATIC;
        PolygonShape rightWallShape = new PolygonShape();
        rightWallShape.setAsBox(0, 1000);
        Body rightWall = world.createBody(rightWallDef);
        FixtureDef rightWallFixture = new FixtureDef();
        rightWallFixture.density = 1;
        rightWallFixture.restitution = 0.3f;
        rightWallFixture.shape = rightWallShape;
        rightWall.createFixture(rightWallFixture);

        BodyDef topWallDef = new BodyDef();
        topWallDef.position.set(0, 240f / 30);
        topWallDef.type = BodyType.STATIC;
        PolygonShape topWallShape = new PolygonShape();
        topWallShape.setAsBox(1000, 0);
        Body topWall = world.createBody(topWallDef);
        FixtureDef topWallFixture = new FixtureDef();
        topWallFixture.density = 1;
        topWallFixture.restitution = 0.3f;
        topWallFixture.shape = topWallShape;
        topWall.createFixture(topWallFixture);
        
        ABCamera camera = new ABCamera(0, 0, 0, 0, ABScreen.getWidth(), ABScreen.getHeight());
		while(!ABScreen.isAskedToClose()){
			
			ABScreen.update(60);
			for(Body body : bodies){
			
			
			 glPushMatrix();
			 Vec2 bodyPosition = body.getPosition().mul(30);
             glTranslatef(bodyPosition.x, bodyPosition.y, 0);
             glRotated(Math.toDegrees(body.getAngle()), 0, 0, 1);
             ABBox.drawBox(-0.75f * 30 * 2, -0.75f * 30 * 2, 0.75f * 30 * 2, 0.75f * 30 * 2);
             glPopMatrix();
			}
			
             if(ABMouse.isLeftMouseButtonPressed()){
            	 Vec2 bodyPosition = new Vec2(ABMouse.getX(), ABMouse.getY()).mul(0.5f).mul(1 / 30f);
                 BodyDef boxDef = new BodyDef();
                 boxDef.position.set(bodyPosition);
                 boxDef.type = BodyType.DYNAMIC;
                 PolygonShape boxShape = new PolygonShape();
                 boxShape.setAsBox(0.75f, 0.75f);
                 Body box = world.createBody(boxDef);
                 FixtureDef boxFixture = new FixtureDef();
                 boxFixture.density = 0.1f;
                 boxFixture.shape = boxShape;
                 box.createFixture(boxFixture);
                 bodies.add(box);
             }
			world.step(1 / 60f, 8, 3);
			ABScreen.refresh();
		}
	}
	
}
