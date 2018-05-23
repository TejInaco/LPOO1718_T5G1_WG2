package com.velesc.game.InputHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.velesc.game.Sprites.CarroControlado;
import com.velesc.game.Sprites.Fisica;


/**
 * Class responsible only for the desktop inputs to control the car player
 * */
public class InputHandlerDesktop {
    Fisica fisica;

    public InputHandlerDesktop(CarroControlado player, float dt){
        this.fisica = new Fisica();

        float vel = player.getLinearVelocity();
        Vector2 pos = player.getBodyCarroControlado().getPosition();

        if(Gdx.input.isKeyPressed(Input.Keys.UP) && vel < fisica.MAX_VELOCITY.y ) { //player.b2body.getWorldCenter()
            player.getBodyCarroControlado().applyLinearImpulse(0, 80f, pos.x, pos.y, true);
            }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.getBodyCarroControlado().applyLinearImpulse(1000f, 0, pos.x, pos.y, true);
            }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.getBodyCarroControlado().applyLinearImpulse(-1000f, 0, pos.x, pos.y, true);
            }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.getBodyCarroControlado().applyLinearImpulse(0, -100f,pos.x,pos.y,true);
            }
        //  Old movement equation
        // new Vector2(-6000f,0), player.b2body.getWorldCenter(),true and
        // used isKeyJustPressed
    }
}
