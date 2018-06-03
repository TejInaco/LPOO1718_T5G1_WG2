package com.velesc.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class responsible for the creation of the Menu options
 * */
public class MenuButtons implements ActionListener {
    private Sprite skin;


    public MenuButtons(Texture texture, float x, float y, float width, float height) {
        skin = new Sprite(texture);
        skin.setPosition(x, y);
        skin.setSize(width, height);
    }
    public Sprite getSkin(){
        return skin;
    }
    /**
     * Checks if menu label was clicked
     * @param ix receives the clicked position x
     * @param iy receives the clicked position y
     *      @return true if the clicked as inside of the label, false otherwise
     * */
    public boolean checkIfClicked (float ix, float iy) {
        if (ix > skin.getX() && ix < skin.getX() + skin.getWidth()) {
            if (iy > skin.getY() && iy < skin.getY() + skin.getHeight()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}

