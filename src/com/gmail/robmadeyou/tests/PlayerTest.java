package com.gmail.robmadeyou.tests;

import com.gmail.robmadeyou.entity.ABNpc;
import com.gmail.robmadeyou.entity.ABPlayer;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * User: mrgadgetz
 * Date: 9/26/13
 * Time: 10:22 PM
 */
 // right click test class and click run as test
@RunWith(JUnit4.class)
public class PlayerTest {
    ABPlayer player = new ABPlayer(10, 20, 30, 40);
    ABNpc enemy = new ABNpc(30, 40, 50, 60);

    /**
     * Need to get the @Before setup annotation working
     * so we don't have to place
     * initialization objects at the top of the class
     */
    @Before
    public void init() {

    }

    @org.junit.Test
    public void playerCoordX() {
        assertEquals(10, (int) player.getX());
    }

    @org.junit.Test
    public void playerCoordY() {
        assertEquals(20, (int) player.getY());
    }

    @org.junit.Test
    public void playerWidth() {
        assertEquals(30, (int) player.getWidth());
    }
    @org.junit.Test
    public void playerHeight() {
        assertEquals(40, (int) player.getHeight());
    }

    @org.junit.Test
    public void nearEnemy() {
        assertTrue(player.isNear(enemy));
    }

    @org.junit.Test
    public void enemyTarget() {
        enemy.setTargetPlayer(player);
        assertEquals(enemy.getTargetPlayer(), player);
    }
}
