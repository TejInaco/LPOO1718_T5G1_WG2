package com.velesc.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.velesc.game.Screens.EcraJogo;

/**
 * NOTE:
 * Our class #velEscaldGamge#  belongs to CLASS game [contain methods set and get screen].
 * This last one implements the <Interface>
 *     ApplicationListener.
 * ApplicationAdapter implements ApplicationListener as well
 * This class allows the developer to implement the ApplicationListener interface without
 * overriding every method.
 *
 * If I changed the extends to ApplicationListener no error will be shown
 * */
public class VelocidadeEscaldante extends Game {

    public static final int alturaVirtual = 400;
    public static final int larguraVirtual = 208;
    /**
     * images in textures*/
    public SpriteBatch batch;



    @Override
	public void create () {
        batch = new SpriteBatch();
        /**the game itslef*/
        setScreen(new EcraJogo(this));
    }

    /**delegates to the playsecreen*/
	@Override
	public void render(){
		super.render();
	}

}
