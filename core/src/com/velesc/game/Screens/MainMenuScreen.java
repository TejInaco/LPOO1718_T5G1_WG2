package com.velesc.game.Screens;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.velesc.game.Assets;
import com.velesc.game.MenuButtons;
import com.velesc.game.VelocidadeEscaldante;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuScreen implements Screen{
    private static final int  BUTTONS_WIDTH = 300;
    private static final int  BUTTONS_HEIGHT = 75;
    private final int POSITION_X = 50;
    private final int POSITION_Y_1_ROW = 300;
    private final int POSITION_Y_2_ROW = 200;
    private final int POSITION_Y_3_ROW = 100;

    private final VelocidadeEscaldante game;

    private OrthographicCamera camera;

    private Assets assets;

    private boolean jumpScreen;
    private MenuButtons buttonPlay;
    private MenuButtons buttonScores;
    private MenuButtons buttonExit;

    private Stage stage;
    private Table table;
    /**
     * Constructor
     * */
    public MainMenuScreen(VelocidadeEscaldante game) {
        this.game = game;

        this.camera = new OrthographicCamera();
        Viewport viewport = new FitViewport(VelocidadeEscaldante.largura, VelocidadeEscaldante.altura);
        viewport.apply();
        this.assets = new Assets();

        jumpScreen = false;

        this.buttonPlay = new MenuButtons(assets.TEXTURE_PLAY,POSITION_X,
                POSITION_Y_3_ROW, BUTTONS_WIDTH, BUTTONS_HEIGHT);
        this.buttonScores = new MenuButtons(assets.TEXTURE_SCORES,POSITION_X,
                POSITION_Y_1_ROW, BUTTONS_WIDTH, BUTTONS_HEIGHT);
        this.buttonExit = new MenuButtons(assets.TEXTURE_EXIT, POSITION_X,
                POSITION_Y_2_ROW, BUTTONS_WIDTH, BUTTONS_HEIGHT);


    }

    @Override
    public void resize(int width, int height) {

    }
    /**
     * Draws Top and Buttom Textures
     * */
    private void drawTopButtomTextures(){
        game.batch.draw(assets.TOP, 0, 400);
        game.batch.draw(assets.BUTTOM, 1, 1);

    }
    /**
     * Show and draw the first screen show to the user
     * */
    private void renderEcraBoasVindas() {
        int ecra_HalfDivision = 2;

        camera.update();
        game.batch.begin();
        game.batch.draw(assets.BACKGROUND, 0, 50);
        drawTopButtomTextures();
        game.font.setColor(Color.BLACK);
        game.font.draw(game.batch, "Welcome to Velocidade Escaldante", 200, 200);
        game.font.draw(game.batch, " Press ENTER or Tap anywhere to begin", VelocidadeEscaldante.largura / ecra_HalfDivision, 180);
        game.batch.end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            jumpScreen = true;
        }
    }
    /**
     * Shows the second screen: the menu options
     * */
    private void renderMenuOptions(){
        camera.update();
        game.batch.begin();

        drawTopButtomTextures();
        actionOnExit();
        actionOnScore();
        actionOnPlay();
        visualEffectExit();
        visualEffectScores();
        visualEffectPlay();

        game.batch.end();
    }
    /**
     * Change textures when mouse is over the exit button
     * */
    private void visualEffectExit() {
        if(Gdx.input.getY() > 180 && Gdx.input.getY() < 280) {
            game.batch.draw(assets.TEXTURE_EXIT, POSITION_X, POSITION_Y_2_ROW, BUTTONS_WIDTH, BUTTONS_HEIGHT);
            game.batch.draw(assets.TEXTURE_PLAYINACTIVE, POSITION_X, POSITION_Y_1_ROW, BUTTONS_WIDTH, BUTTONS_HEIGHT);
            game.batch.draw(assets.TEXTURE_SCORESINACTIVE, POSITION_X, POSITION_Y_3_ROW, BUTTONS_WIDTH, BUTTONS_HEIGHT);
        }
    }
    /**
     * Change textures when mouse is over the scores button
     * */
    private void visualEffectScores() {
        if(Gdx.input.getY() > 290 && Gdx.input.getY() < 370) {
            game.batch.draw(assets.TEXTURE_SCORES,POSITION_X,POSITION_Y_3_ROW, BUTTONS_WIDTH, BUTTONS_HEIGHT);
            game.batch.draw(assets.TEXTURE_PLAYINACTIVE, POSITION_X, POSITION_Y_1_ROW, BUTTONS_WIDTH, BUTTONS_HEIGHT);
            game.batch.draw(assets.TEXTURE_EXITINACTIVE, POSITION_X, POSITION_Y_2_ROW, BUTTONS_WIDTH, BUTTONS_HEIGHT);
        }
    }
    /**
    * Change textures when mouse is over the play button
    * */
    private void visualEffectPlay(){
            game.batch.draw(assets.TEXTURE_PLAY, POSITION_X, POSITION_Y_1_ROW, BUTTONS_WIDTH, BUTTONS_HEIGHT);
            game.batch.draw(assets.TEXTURE_EXIT, POSITION_X, POSITION_Y_2_ROW, BUTTONS_WIDTH, BUTTONS_HEIGHT);
            game.batch.draw(assets.TEXTURE_SCORES, POSITION_X, POSITION_Y_3_ROW, BUTTONS_WIDTH, BUTTONS_HEIGHT);

    }
    /**
     * Defines action when Exit is pressed
     * */
    private void actionOnExit(){
        if (buttonExit.checkIfClicked(Gdx.input.getX(),Gdx.input.getY()) && Gdx.input.isTouched()) {
            assets.dispose();
            Gdx.app.exit();
        }
    }
    /**
     * Defines action when Score is pressed
     * */
    private void actionOnScore(){
        if (buttonScores.checkIfClicked(Gdx.input.getX(),Gdx.input.getY()) && Gdx.input.isTouched()) {
            assets.dispose();
            Gdx.app.exit();
        }
    }
    /**
     * Defines action when Play is pressed
     * */
    private void actionOnPlay() {
        if (buttonPlay.checkIfClicked(Gdx.input.getX(),Gdx.input.getY()) && Gdx.input.isTouched()) {
            this.dispose();
            game.setScreen(new EcraJogo(game));
        }


    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(!jumpScreen){
            renderEcraBoasVindas();
        }else{
            renderMenuOptions();
        }

    }

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
