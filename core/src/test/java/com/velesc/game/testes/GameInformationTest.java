package com.velesc.game.testes;

import com.velesc.game.Screens.GameInformation;
import org.junit.Assert;
import org.junit.Test;

public class GameInformationTest {

    @Test
    public void addPontos() {
        GameInformation ecra = new GameInformation();
        ecra.setPontos(0);
        ecra.setPontos(30);
        Assert.assertEquals(30, ecra.getPontos());
        ecra.addPontos(30);
        Assert.assertEquals(60,ecra.getPontos());
    }

    @Test
    public void addVelocidade() {
    }
}