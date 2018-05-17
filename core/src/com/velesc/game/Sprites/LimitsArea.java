package com.velesc.game.Sprites;

import com.badlogic.gdx.math.Rectangle;

public class LimitsArea {

    private Rectangle bounds;

    public LimitsArea(int x, int y, int width, int height) {
        bounds = new Rectangle(x, y, width, height);
    }

    public Rectangle getBounds() {
        return bounds;
    }
}