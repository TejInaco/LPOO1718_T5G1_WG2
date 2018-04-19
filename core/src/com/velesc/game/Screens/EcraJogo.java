package com.velesc.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.velesc.game.Scenes.EcraVizualizado;
import com.velesc.game.VelocidadeEscaldante;

/**Play Screen*/
public class EcraJogo implements Screen {

    private VelocidadeEscaldante game;

    private OrthographicCamera gameCamera;
    private FitViewport gamePort;
    private EcraVizualizado hud;
    /**NOT IN USE*/
    //Texture texture;
    /***/

    /**Constructor*/
    public EcraJogo(VelocidadeEscaldante game){
        this.game = game;
        //adicionar textura jpg TODO
        //texture = new Texture(Gdx.files.internal("resources/estradaTeste.jpg"));
        //"resources/estradaTeste.jpg"
        gameCamera = new OrthographicCamera();
        gamePort = new FitViewport(VelocidadeEscaldante.larguraVirtual, VelocidadeEscaldante.alturaVirtual, gameCamera);
        /**Test with */
        /**could use the Screenviewport*/
        hud = new EcraVizualizado(game.batch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
    Gdx.gl.glClearColor(1,0,0,1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    /** to render what camera can see*/
//    game.batch.setProjectionMatrix(gamecamera.combined);
    /***/
//    game.batch.begin();
//    game.batch.draw(texture,0,0);
//    game.batch.end();
    game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
    hud.stage.draw();
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
