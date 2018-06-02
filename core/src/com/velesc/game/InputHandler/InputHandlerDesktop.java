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
    private float impulseBreak = -100f;

    public InputHandlerDesktop() {

    }

    public void updateInputDesktop(CarroControlado player){
        float vel = player.getLinearVelocity();
        Vector2 pos = player.getBodyCarroControlado().getPosition();

        if (Gdx.input.isKeyPressed(Input.Keys.UP) && vel < player.getFisica().MAX_VELOCITY.y) {
            player.getBodyCarroControlado().applyLinearImpulse(0, player.getFisica().RISE_SPEED, pos.x, pos.y, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (!player.getFlagBoundariesRight()) {
                player.getBodyCarroControlado().applyLinearImpulse(player.getFisica().IMPULSE_RIGHT, 0, pos.x, pos.y, true);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (!player.getFlagBoundariesLeft()) {
                player.getBodyCarroControlado().applyLinearImpulse(player.getFisica().IMPULSE_LEFT, 0, pos.x, pos.y, true);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.getBodyCarroControlado().applyForceToCenter(0,player.getFisica().RISE_SPEED_BACKWARDS,false);
//TODO condicao para o stop totalmente
           if(player.getBodyCarroControlado().getLinearVelocity().isZero()){
               player.getBodyCarroControlado().getLinearVelocity().setZero();
           }
        }
    }

}
