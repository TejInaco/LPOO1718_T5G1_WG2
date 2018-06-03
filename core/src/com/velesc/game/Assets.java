package com.velesc.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.io.File;

/**
 * Class to store and load every texture
 * */
public final class Assets {
    //Main menu
    public final Texture TEXTURE_PLAY = new Texture("play.png");
    public final Texture TEXTURE_EXIT = new Texture("exit.png");
    public final Texture TEXTURE_SCORES = new Texture("Highscores.png");
    public final Texture TEXTURE_PLAYINACTIVE = new Texture("flame_barrier.png");
    public final Texture TEXTURE_EXITINACTIVE = new Texture("flame_barrier.png");
    public final Texture TEXTURE_SCORESINACTIVE = new Texture("flame_barrier.png");
    //Ecra inicial
    public final Texture BACKGROUND = new Texture("logo.png");
    public final Texture TOP = new Texture("Velocidade.png");
    public final Texture BUTTOM = new Texture("Escaldante.png");
    public final Texture CAMARO = new Texture("camaro.png");
    //Cars on the road
    public final Texture CAR_0 = new Texture("vehic_car0.png");
    public final Texture CAR_1 = new Texture("vehic_car8.png");
    public final Texture CAR_2 = new Texture("vehic_car9.png");
    public final Texture CAR_3 = new Texture("vehic_taxi0.png");


    public Assets(){

    }
    public Texture getCamaro(){
        return CAMARO;
    }
    public void dispose(){
        TEXTURE_PLAY.dispose();
        TEXTURE_EXIT.dispose();
        TEXTURE_SCORES.dispose();
        TEXTURE_PLAYINACTIVE.dispose();
        TEXTURE_EXITINACTIVE.dispose();
        TEXTURE_SCORESINACTIVE.dispose();
        BACKGROUND.dispose();
        TOP.dispose();
        BUTTOM.dispose();
        CAMARO.dispose();
        CAR_0.dispose();
        CAR_1.dispose();
        CAR_2.dispose();
        CAR_3.dispose();
    }
}
