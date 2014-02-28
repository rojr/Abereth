package com.age.world;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by apex on 03/02/14.
 */
public interface Physics {
    /**
     * Returns X as the getDrawX coordinate
     * Returns Y as the getDrawY + getDrawHeight coordinate
     * Returns Z as the width
     * @return
     */
    Vector3f getBottom();
    /**
     * Returns X as the getDrawX coordinate
     * Returns Y as the getDrawY coordinate
     * Returns Z as the width
     * @return
     */
    Vector3f getTop();
    /**
     * Returns X as the getDrawX coordinate
     * Returns Y as the getDrawY coordinate
     * Returns Z as the height
     * @return
     */
    Vector3f getLeft();
    /**
     * Returns X as the getDrawX + getDrawWidth coordinate
     * Returns Y as the getDrawY coordinate
     * Returns Z as the Height
     * @return
     */
    Vector3f getRight();

    /**
     * The "weight" of the object, the larger the value the
     * faster the speed of fall
     * @return
     */
    float getGravity();
    /**
     * The "weight" of the object, the larger the value the
     * faster the speed of fall
     * Default is 1.
     * @param gravity anything above 0. With 0 - 1 being a slow descend and 1+ increasing in speed
     */
    void setGravity(float gravity);

    Vector2f getVelocity();
    void setVelocity(Vector2f vec);
}
