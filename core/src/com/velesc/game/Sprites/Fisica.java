package com.velesc.game.Sprites;

import com.badlogic.gdx.math.Vector2;


public class Fisica {
    public Vector2 MAX_VELOCITY = new Vector2(0,2);

    public static final Vector2 INCREASE_VELOCITY = new Vector2(0, 50);
    public static final Vector2 MINVELOCITY = new Vector2(0, 20);
    public static final float IMPULSE_RIGHT = 1000f;
    public static final float IMPULSE_LEFT = -1000f;
    public static final float RISE_SPEED_BACKWARDS = -80f;
    public static final float RISE_SPEED = 20f;
    public static final float DENSITY = 0.15f;
    public static final float RESTITUTION = 0.5f;
    public static final float FRICTION = 0.5f;
    public float actualSpeed = 0;

    /**
     * Default constructor. Class responsible for the physics values and forces, maximum speeds and impulses
     * */
    public Fisica(){
    }
    /**
     * Set the car speed hold variable
     * @param actualSpeed
     * */
    public void setActualSpeed(float actualSpeed) {
        this.actualSpeed = actualSpeed;
    }
    /**
     * @return car speed
     * */
    public float getActualSpeed() {
        return actualSpeed;
    }
    /**
     * Update MAX_VELOCITY when there was a level change in the game.
     * The next level will increase the maximum velocity of the player
     * @param level receives actual game level
     * */
    public void update(int level){
        float novaVelocidade = MAX_VELOCITY.y + level;
        MAX_VELOCITY.set(0,novaVelocidade);
    }
}
