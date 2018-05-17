package com.velesc.game;
import com.velesc.game.MainMenuScreen;
import com.badlogic.gdx.Application;
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

    public static final int largura = 640;
    public static final int altura = 1024;
    //Pixels per meter
    public static final float PPM = 16;

    public static boolean IS_MOBILE = false;
    //images in textures
    public SpriteBatch batch;
    public BitmapFont font;


    @Override
	public void create () {
        batch = new SpriteBatch();
        //the game itself
        font = new BitmapFont();


        if(Gdx.app.getType() == Application.ApplicationType.Android ) {
            IS_MOBILE = true;
        }
        //MainMenuScreen
        this.setScreen(new EcraJogo(this));
    }

    //delegates to the play screen
	@Override
	public void render(){
		super.render();
	}
	@Override
    public void dispose(){
        batch.dispose();
        font.dispose();
    }

}
