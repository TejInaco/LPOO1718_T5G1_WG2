package com.velesc.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuButtons {
    private Sprite skin;


    public MenuButtons(Texture texture, float x, float y, float width, float height) {
        skin = new Sprite(texture);
        skin.setPosition(x, y);
        skin.setSize(width, height);
    }

    public boolean checkIfClicked (float ix, float iy) {
        if (ix > skin.getX() && ix < skin.getX() + skin.getWidth()) {
            if (iy > skin.getY() && iy < skin.getY() + skin.getHeight()) {
                return true;
            }
        }
        return false;
    }

}

