package com.velesc.game;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.velesc.game.Screens.*;

/**
 * Class Start-up
 * */
public class VelocidadeEscaldante extends Game {

    public static final int largura = 512;
    public static final int altura = 720;

    public static final float PPM = 16; //Pixels per meter

    private static boolean IS_MOBILE = false;
    private int level = 1;
    //images in textures
    public SpriteBatch batch;
    public BitmapFont font;


    @Override
	public void create () {
        batch = new SpriteBatch();
        font = new BitmapFont();


        if(Gdx.app.getType() == Application.ApplicationType.Android ) {
            IS_MOBILE = true;
        }
        // EcraJogo
        this.setScreen(new MainMenuScreen(this));
    }
    public void setLevel(){
        level += 1;
    }
    public int getLevel(){
        return level;
    }

    public boolean getIsMobile()
    {
        return IS_MOBILE;
    }

	@Override
	public void render() {
        super.render();
	}
	@Override
    public void dispose(){
        batch.dispose();
        font.dispose();
    }

}
