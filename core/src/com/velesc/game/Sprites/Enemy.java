package com.velesc.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.velesc.game.Screens.EcraJogo;


abstract class Enemy extends Sprite {

    protected World world;
    protected EcraJogo screen;

    public Body b2body;
    public Vector2 velocity;

    public Enemy(EcraJogo screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defineEnemy();
        velocity = new Vector2(0, 1000f);
        b2body.setActive(false);
    }

    /**
     * Defines the fixtures and body proprieties
     * */
    protected abstract void defineEnemy();

    public abstract void update(float dt);

    /**
     * Collision checker
     * @param posPlayerX actual player position in X axis
     * @param posPlayerY actual player position in Y axis
     * @param largura sprite wight
     * @param altura sprite height
     * @return true if there was a collision between bodies
     * */
    public abstract boolean hitByEnemy(float posPlayerX, float posPlayerY, float largura, float altura);

}
