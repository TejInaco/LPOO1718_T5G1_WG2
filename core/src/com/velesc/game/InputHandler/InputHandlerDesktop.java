package com.velesc.game.InputHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.velesc.game.Sprites.CarroControlado;

public class InputHandlerDesktop {
    //TODO Retirar esta macro
    float MAX_VELOCITY = 100;

    public InputHandlerDesktop(CarroControlado player, float dt){

        //TODO thing about adding && player.b2.body.getLinear Velocity().y <= 2

        Vector2 vel = player.b2body.getLinearVelocity();
        Vector2 pos = player.b2body.getPosition();

        if(Gdx.input.isKeyPressed(Input.Keys.UP) && vel.x < MAX_VELOCITY ) { //player.b2body.getWorldCenter()
            player.b2body.applyLinearImpulse(0, 80f, pos.x, pos.y, true);
            }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.b2body.applyLinearImpulse(1000f, 0, pos.x, pos.y, true);
            }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.b2body.applyLinearImpulse(-1000f, 0, pos.x, pos.y, true);
            }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            player.b2body.applyLinearImpulse(0, -100f,pos.x,pos.y,true);
            }
        //  Old movement equation
        // new Vector2(-6000f,0), player.b2body.getWorldCenter(),true and
        // used isKeyJustPressed
    }
}
