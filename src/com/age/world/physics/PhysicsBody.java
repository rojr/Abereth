package com.age.world.physics;

import org.lwjgl.util.vector.Vector2f;

/**
 * Created by apex on 30/03/14.
 */
public interface PhysicsBody {
    float getMass();
    Vector2f getVelocity();
    PhysicsBody getColision();
}
