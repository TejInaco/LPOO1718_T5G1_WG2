package com.velesc.game.Screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.velesc.game.Assets;
import com.velesc.game.Screens.EcraJogo;
import com.velesc.game.VelocidadeEscaldante;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MainMenuScreen implements Screen {
    private static final int  PLAY_BUTTON_WIDTH = 200;
    private static final int  PLAY_BUTTON_HEIGHT = 100;
    private static final int  HIGHSCORES_BUTTON_WIDTH = 300;
    private static final int  HIGHSCORES_BUTTON_HEIGHT = 100;
    private static final int EXIT_BUTTON_WIDTH = 200;
    private static final int EXIT_BUTTON_HEIGHT = 100;
    public int ecra_HalfDivision = 2;

    final VelocidadeEscaldante game;

    private OrthographicCamera camera;
    private Viewport viewport;
    protected Stage stage;

    Assets assets;

    boolean jumpScreen;

    /**Cosntructor*/
    public MainMenuScreen(VelocidadeEscaldante game){
        this.game = game;
        AssetManager manager = new AssetManager();

        camera = new OrthographicCamera();
        viewport = new FitViewport(game.largura,game.altura);
        viewport.apply();
        assets = new Assets();

        jumpScreen = false;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render(float delta){

        if(!jumpScreen){    //primeiro ecran
            Gdx.gl.glClearColor(0,0,0,1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            camera.update();
            game.batch.begin();
            //Logo Drawing
            //assetsManager.load(Assets.BACKGROUND)

            game.batch.draw(assets.BACKGROUND,0,50);
            //top
            game.batch.draw(assets.TOP,0,400 );
            //button
            game.batch.draw(assets.BUTTOM ,1,1 );
            game.font.setColor(Color.BLACK);
            game.font.draw(game.batch, "Welcome to Velocidade Escaldante", 200, 200);
            game.font.draw(game.batch, " Press ENTER or Tap anywhere to begin", game.largura/ecra_HalfDivision, 180);
            game.batch.end();

            if( Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER) ) {
                jumpScreen = true;            }
        }else{  //segundo ecran
            Gdx.gl.glClearColor(65,65,0,1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            camera.update();
            game.batch.begin();
            //top
            game.batch.draw(assets.TOP,0,400 );
            //button
            game.batch.draw(assets.BUTTOM,1,1 );

            //poderia por no x Velcoidade.largura/2 && Gdx.input.getY() > 300)
            //|| Gdx.input.isKeyJustPressed(Input.Keys.ENTER)
            if( Gdx.input.getX() > 50 && Gdx.input.getX() < 250 ){ //eixo do xx
                //Exit Button selection
                if(Gdx.input.getY() > 180 && Gdx.input.getY() < 280) {
                    game.batch.draw(assets.TEXTURE_EXIT, 50, 200, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
                    if (Gdx.input.isTouched()) {
                        assets.dispose();
                        Gdx.app.exit();
                    }
                    game.batch.draw(assets.TEXTURE_PLAYINACTIVE, 50, 300, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
                    game.batch.draw(assets.TEXTURE_SCORESINACTIVE, 50, 100, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
                }
                //Highscores button selection
                if(Gdx.input.getY() > 290 && Gdx.input.getY() < 370) {
                    game.batch.draw(assets.TEXTURE_SCORES,50,100, HIGHSCORES_BUTTON_WIDTH, HIGHSCORES_BUTTON_HEIGHT);
                    if (Gdx.input.isTouched()) {
                        //TODO Substituir por o ecran scores
                        Gdx.app.exit();
                    }
                    game.batch.draw(assets.TEXTURE_PLAYINACTIVE, 50, 300, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
                    game.batch.draw(assets.TEXTURE_EXITINACTIVE, 50, 200, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
                }
                //Play Button selection 250 350
                if(Gdx.input.getY() > 70 && Gdx.input.getY() < 170) {
                    game.batch.draw(assets.TEXTURE_PLAY, 50, 300, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
                    if (Gdx.input.isTouched()) {
                        this.dispose();
                        //TODO teste se inicia o jogo
                        game.setScreen(new EcraJogo(game));
                    }
                    game.batch.draw(assets.TEXTURE_EXITINACTIVE, 50, 200, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
                    game.batch.draw(assets.TEXTURE_SCORESINACTIVE, 50, 100, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
                }

            }else{
                game.batch.draw(assets.TEXTURE_PLAYINACTIVE, 50, 300, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
                game.batch.draw(assets.TEXTURE_EXITINACTIVE, 50, 200, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
                game.batch.draw(assets.TEXTURE_SCORESINACTIVE, 50, 100, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            }


            boolean goDown = false;
            boolean goUp = false;
            if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
                goDown = true;
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
                goUp = false;
            }
             game.batch.end();

           /* //Stage should controll input
            Gdx.input.setInputProcessor(stage);
            //Create table
            Table mainTable = new Table();
            //Set Table to fill stage
            mainTable.center();
*/
//            buttonPlay = new MenuButtons(texture_play,50,50,20,20);
//            buttonExit = new MenuButtons(texture_exit,0,0,20,20);

//        MenuButtons instructions = new MenuButtons();
//        MenuButtons scores = new MenuButtons();


        }

    }

//    public void menu(){
//        if(game.IS_MOBILE){
//            menuForMobile();
//        }
//        else if(!game.IS_MOBILE){
//            menuForDesktop();
//        }
//    }
//    //for android
//    public void menuForMobile(){
//
//    }
//    public void menuForDesktop(){
//        this.show();
//        camera.update();
//    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose(){

    }


}
