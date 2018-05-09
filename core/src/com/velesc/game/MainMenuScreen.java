package com.velesc.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
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

    Texture texture_background;
    Texture texture_top;
    Texture texture_buttom;

    Texture texture_play;
    Texture texture_exit;
    Texture texture_scores;

    MenuButtons buttonPlay;
    MenuButtons buttonExit;
    MenuButtons buttonInstructions;
    MenuButtons buttonScores;


    boolean jumpScreen;

    /**Cosntructor*/
    public MainMenuScreen(VelocidadeEscaldante game){
        this.game = game;

        camera = new OrthographicCamera();
        viewport = new FitViewport(game.largura,game.altura);
        viewport.apply();


        texture_background = new Texture(Gdx.files.internal("android/assets/logo.png"));
        texture_top = new Texture(Gdx.files.internal("android/assets/Velocidade.png"));
        texture_buttom = new Texture(Gdx.files.internal("android/assets/Escaldante.png"));

        texture_play = new Texture(Gdx.files.internal("android/assets/play.png"));
        texture_exit = new Texture(Gdx.files.internal("android/assets/exit.png"));
        texture_scores = new Texture(Gdx.files.internal("android/assets/Highscores.png"));

        jumpScreen = false;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render(float delta){

        if(!jumpScreen){
            Gdx.gl.glClearColor(0,0,0,1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            camera.update();
            game.batch.begin();
            //Logo Drawing
            game.batch.draw(texture_background,0,50);
            //top
            game.batch.draw(texture_top,0,400 );
            //button
            game.batch.draw(texture_buttom,1,1 );
            game.font.setColor(Color.BLACK);
            game.font.draw(game.batch, "Welcome to Velocidade Escaldante", 200, 200);
            game.font.draw(game.batch, " Press ENTER or Tap anywhere to begin", game.largura/ecra_HalfDivision, 180);
            game.batch.end();

            if( Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER) ) {
                jumpScreen = true;            }
        }else{
            Gdx.gl.glClearColor(65,65,0,1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            camera.update();
            game.batch.begin();
            //top
            game.batch.draw(texture_top,0,400 );
            //button
            game.batch.draw(texture_buttom,1,1 );

            //poderia por no x Velcoidade.largura/2
            if(Gdx.input.getX() < 50 && Gdx.input.getY() > 300) {
                game.batch.draw(texture_play, 50, 300, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
                if(Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
                    this.dispose();
                    game.setScreen(new EcraJogo(game));
                }
            } else{
                // por aqui a textura do drwa nao focalizado
            }

            if(Gdx.input.getX() < 50 && Gdx.input.getY() > 300){
                game.batch.draw(texture_exit, 50, 200, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
                if(Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
                    Gdx.app.exit();
                }
            } else{
             //exit nao focalizado
            }

            if(Gdx.input.getX() < 50 && Gdx.input.getY() > 300){
                game.batch.draw(texture_scores,50,100, HIGHSCORES_BUTTON_WIDTH, HIGHSCORES_BUTTON_HEIGHT);
            } else{
                //highscores nao focalizado
            }
            boolean goDown = false;
            boolean goUp = false;
            if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
                goDown = true;
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
                goUp = false;
            }
            switch() {

                case 1:
                    break;
                case 2:
                    break;
                case3:
                    break;
            }
            game.batch.end();

            //Stage should controll input
            Gdx.input.setInputProcessor(stage);
            //Create table
            Table mainTable = new Table();
            //Set Table to fill stage
            mainTable.center();

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
     texture_background.dispose();
     texture_buttom.dispose();
     texture_top.dispose();

    }


}
