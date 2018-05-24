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
    CarroControlado player;

    public InputHandlerDesktop(CarroControlado player, float dt) {
        this.player = player;

    }

    public void inputManager(){
        float vel = player.getLinearVelocity();
        Vector2 pos = player.getBodyCarroControlado().getPosition();

        if (Gdx.input.isKeyPressed(Input.Keys.UP) && vel < player.getFisica().MAX_VELOCITY.y) { //player.b2body.getWorldCenter()
            player.getBodyCarroControlado().applyLinearImpulse(0, 80f, pos.x, pos.y, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (!player.getFlagBoundariesRight()) {
                player.getBodyCarroControlado().applyLinearImpulse(1000f, 0, pos.x, pos.y, true);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (!player.getFlagBoundariesLeft()) {
                player.getBodyCarroControlado().applyLinearImpulse(-1000f, 0, pos.x, pos.y, true);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.getBodyCarroControlado().applyLinearImpulse(0, -100f, pos.x, pos.y, true);
        }
    }


}
