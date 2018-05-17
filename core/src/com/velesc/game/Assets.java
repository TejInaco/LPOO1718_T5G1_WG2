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

    public Assets(){

    }

//    public static final AssetDescriptor<Music>
//    public static final AssetDescriptor<TextureAtlas>
//    public static final AssetDescriptor<Sound>

    //Ecra inicial e Main menu
    public static final AssetDescriptor<Texture> BACKGROUND =
            new AssetDescriptor<Texture>("android" + File.separator + "assets" + File.separator + "logo.png", Texture.class);
    public static final AssetDescriptor<Texture> TOP =
            new AssetDescriptor<Texture>("android" + File.separator + "assets" + File.separator + "Velocidade.png", Texture.class);
    public static final AssetDescriptor<Texture> Buttom =
            new AssetDescriptor<Texture>("android" + File.separator + "assets" + File.separator + "Escaldante.png", Texture.class);
//Main Menu
//    public static final AssetDescriptor<Texture> texture_play = new AssetDescriptor<Texture>(Gdx.files.internal("android/assets/play.png"));
//    public static final AssetDescriptor<Texture> texture_exit = new AssetDescriptor<Texture>(Gdx.files.internal("android/assets/exit.png"));
//    public static final AssetDescriptor<Texture> texture_scores = new AssetDescriptor<Texture>(Gdx.files.internal("android/assets/Highscores.png"));
//
//    public static final AssetDescriptor<Texture> texture_playInactive = new AssetDescriptor<Texture>(Gdx.files.internal("android/assets/flame_barrier.png"));
//    public static final AssetDescriptor<Texture> texture_exitInactive = new AssetDescriptor<Texture>(Gdx.files.internal("android/assets/flame_barrier.png"));
//    public static final AssetDescriptor<Texture> texture_scoresInactive = new AssetDescriptor<Texture>(Gdx.files.internal("android/assets/flame_barrier.png"));


}
