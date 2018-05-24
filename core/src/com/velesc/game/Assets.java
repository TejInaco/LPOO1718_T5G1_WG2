package com.velesc.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.io.File;

/**
 * Class to store and acess every AssetDescriptor
 * */
public final class Assets {
    public static Texture TEXTURE_PLAY = new Texture(Gdx.files.internal("android/assets/play.png"));
    public static Texture TEXTURE_EXIT = new Texture(Gdx.files.internal("android/assets/exit.png"));
    public static Texture TEXTURE_SCORES = new Texture(Gdx.files.internal("android/assets/Highscores.png"));
    public static Texture TEXTURE_PLAYINACTIVE = new Texture(Gdx.files.internal("android/assets/flame_barrier.png"));
    public static Texture TEXTURE_EXITINACTIVE = new Texture(Gdx.files.internal("android/assets/flame_barrier.png"));
    public static Texture TEXTURE_SCORESINACTIVE = new Texture(Gdx.files.internal("android/assets/flame_barrier.png"));
    //Ecra inicial e Main menu
    public static Texture BACKGROUND = new Texture(Gdx.files.internal("android/assets/logo.png"));
    public static Texture TOP = new Texture(Gdx.files.internal("android/assets/Velocidade.png"));
    public static Texture BUTTOM = new Texture(Gdx.files.internal("android/assets/Escaldante.png"));
    public static Texture CAMARO = new Texture(Gdx.files.internal("android/assets/camaro.png"));
    //Cars on the road
    public static Texture CAR_1 = new Texture(Gdx.files.internal("android/assets/vehic_car0.png"));
    public static Texture CAR_2 = new Texture(Gdx.files.internal("android/assets/vehic_car2.png"));
    public static Texture CAR_3 = new Texture(Gdx.files.internal("android/assets/vehic_car3.png"));
    public static Texture CAR_4 = new Texture(Gdx.files.internal("android/assets/vehic_taxi0.png"));

    public Assets(){

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
        CAR_1.dispose();
        CAR_2.dispose();
        CAR_3.dispose();
        CAR_4.dispose();
    }
}
