package com.velesc.game.testes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.velesc.game.Screens.EcraVizualizado;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EcraVizualizadoTest {

    @Test
    public void addPontos() {
        EcraVizualizado ecra = new EcraVizualizado();
        ecra.setPontos(0);
        ecra.setPontos(30);
        Assert.assertEquals(30, ecra.getPontos());
//        ecra.addPontos(30);
//        Assert.assertEquals(60,ecra.getPontos());
    }

    @Test
    public void addVelocidade() {
    }
}