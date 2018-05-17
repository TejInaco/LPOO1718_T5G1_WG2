package com.velesc.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;

public class MainMenuChoice implements Screen {
    final VelocidadeEscaldante game;

    private OrthographicCamera camera;
    protected Stage stage;
    private Viewport viewport;

    Texture texture_top;
    Texture texture_buttom;

    Texture texture_play;
    Texture texture_exit;
    Texture texture_scores;

    MenuButtons buttonPlay;
    MenuButtons buttonExit;
    MenuButtons buttonInstructions;
    MenuButtons buttonScores;


    public MainMenuChoice(VelocidadeEscaldante game){
        this.game = game;

        camera = new OrthographicCamera();

        viewport = new FitViewport(game.largura,game.altura);
        viewport.apply();

        texture_top = new Texture(Gdx.files.internal("android/assets/Velocidade.png"));
        texture_buttom = new Texture(Gdx.files.internal("android/assets/Escaldante.png"));

        texture_play = new Texture(Gdx.files.internal("android/assets/playMode.png"));
        texture_exit = new Texture(Gdx.files.internal("android/assets/exit.png"));
        texture_scores = new Texture(Gdx.files.internal("android/assets/Highscores.png"));


        stage = new Stage(viewport);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(165,165,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.begin();
        game.batch.draw(texture_top,0,400 );
        game.batch.draw(texture_buttom,1,1 );
        game.batch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose(){
        texture_buttom.dispose();
        texture_exit.dispose();
        texture_play.dispose();
        texture_scores.dispose();
       // texture_top.dispose();
    }
}
