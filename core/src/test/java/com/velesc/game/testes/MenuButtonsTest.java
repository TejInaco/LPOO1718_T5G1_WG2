package com.velesc.game.testes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.velesc.game.*;
import com.velesc.game.MenuButtons;
import org.junit.Assert;
import org.junit.Test;


import static org.junit.Assert.*;

public class MenuButtonsTest {
    Assets assets;
    Texture texture;
    @Test
    public void checkIfClicked() {
        assets = new Assets();
        texture  = new Texture(Gdx.files.internal("android/assets/play.png"));
        MenuButtons teste1 = new MenuButtons(texture,2,2,20, 20);
        Assert.assertFalse(teste1.checkIfClicked(1,20));
        Assert.assertFalse(teste1.checkIfClicked(23,20));
        Assert.assertTrue(teste1.checkIfClicked(3,20));

    }
}