package com.velesc.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.velesc.game.VelocidadeEscaldante;


public class EcraJogo implements Screen {

    private VelocidadeEscaldante game;
    Texture texture;
    private OrthographicCamera gamecamera;
    private Viewport gamePort;


    /**Constructor*/
    public EcraJogo(VelocidadeEscaldante game){
        this.game = game;
        //adicionar textura jpg TODO
        texture = new Texture("resources/estradaTeste.jpg");
        gamePort = new StretchViewport(VelocidadeEscaldante.larguraVirtual, VelocidadeEscaldante.alturaVirtual, gamecamera);
        /**Test with */
        /**could use the Fitviewport*/
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
    Gdx.gl.glClearColor(1,0,0,1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    /** to render what camera can see*/
    game.batch.setProjectionMatrix(gamecamera.combined);
    /***/
    game.batch.begin();
    game.batch.draw(texture,0,0);
    game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
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
    public void dispose() {

    }
}
