package com.velesc.game.Sprites;

import com.badlogic.gdx.math.Vector2;

public class Fisica {
    public Vector2 MAX_VELOCITY = new Vector2(0,2);

    public static final Vector2 MAXVELOCITY = new Vector2(0, 500);
    public static final Vector2 MINVELOCITY = new Vector2(0, 20);
    public static final Vector2 ACCELERATION = new Vector2(0, 40);
    public static final Vector2 DECELERATION = new Vector2(0, -120);
    public static final Vector2 MEDIUM_DRIVER = new Vector2(0,1);
    public static final float IMPULSE_RIGHT = 1f;
    public static final float IMPULSE_LEFT = 1f;
    public static final float IMPULSE_BACK = 1f;
    public static final float IMPULSE_FRONT = 1f;
    public static final float DENSITY = 0.15f;
    public static final float RESTITUTION = 0.5f;
    public static final float FRICTION = 0.5f;

    public Fisica(){
    }
}
