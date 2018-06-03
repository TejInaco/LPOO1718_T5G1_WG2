package com.velesc.game.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.velesc.game.VelocidadeEscaldante;

/**
 *  Class responsible for visualization of the time, score, and level on the game
 *
 * */
public class GameInformation implements Disposable{

    //Scene2d.ui Stage and its own viewport for HUD
    public Stage stage;
    private Viewport viewport;

    //Time and score for the car Tracking variables
    private float timeCount = 0;
    private int tempoContador = 0;
    private int pontos = 0;
    private int velocidadeCarro = 0;
    private final int INCREASE_POINTS_BY = 2;
    private int level = 0;
    Table table;
    //Scene 2d widgets

    Label pontosLabel;
    Label pontos_NameLabel;

    Label tempoContador_NameLabel;
    Label tempoContadorLabel;

    Label velocidadeCarroLabel;
    Label velocidadeCarro_NameLabel;

    Label levelLabel;
    Label level_NameLabel;

    public GameInformation(){}

    public GameInformation(SpriteBatch sb) {
        tempoContador = 0;
        pontos = 0;
        velocidadeCarro = 0;

        viewport = new FitViewport(VelocidadeEscaldante.largura,VelocidadeEscaldante.altura, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        table = new Table();
        setLabelsPosition();

        tempoContadorLabel = new Label(String.format("%03d",tempoContador),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        pontosLabel = new Label(String.format("%06d",pontos),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        velocidadeCarroLabel = new Label(String.format("%03d", velocidadeCarro),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label(String.format("%02d", level),new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        tempoContador_NameLabel = new Label("T E M P O",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        pontos_NameLabel = new Label("P O N T O S",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        velocidadeCarro_NameLabel = new Label("Velocidade",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        level_NameLabel = new Label("Level",new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        setLabelNames();
        setLabelsValues();
        stage.addActor(table);
    }
    /**
     * Draw table on top screen
     * */
    public void setLabelsPosition(){
        this.table.top();
        this.table.setFillParent(true);
    }
    /**
     * Draw the first line with the name's label
     * */
    public void setLabelNames(){
        this.table.add(tempoContador_NameLabel).expandX().padTop(10);
        this.table.add(pontos_NameLabel).expandX().padTop(10);
        this.table.add(velocidadeCarro_NameLabel).expandX().padTop(10);
        this.table.add(level_NameLabel).expandX().padTop(10);

    }
    /**
     * Draw the second line of the table with current values
     * */
    public void setLabelsValues(){
        table.row();
        table.add(tempoContadorLabel).expandX();
        table.add(pontosLabel).expandX();
        table.add(velocidadeCarroLabel).expandX();
        table.add(levelLabel).expandX();

    }
    public void setPontos(int value) {
        pontos = value;
    }
    /**
     * Function to update the car velocity
     * @param value Actual velocity of the car
     * */
    public void setVelocidadeCarro(float value) {
        velocidadeCarro = (int) value;
    }

    public int getPontos(){
        return pontos;
    }
    public int getVelocidadeCarro() {
        return velocidadeCarro;
    }
    public void setWorldTimer(Integer value) {
        this.tempoContador  = value;
    }
    public void setLevel(int value){
        this.level = value;
    }
    public void update(float dt){
        timeCount += dt;
        if(timeCount >= 1){
            tempoContador++;
            timeCount = 0;
        }
    }
    /**
     * Function to increase the number of points obtained
     * @param value Number of points to add
     * */
    public void addPontos(float value, Vector2 maximum) {
        if(value >= maximum.y) {
            this.pontos += INCREASE_POINTS_BY;
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
    /**
     * Function to update the labels in the game
     * */
    public void updateLabels(){
        pontosLabel.setText(String.format("%06d",pontos));
        velocidadeCarroLabel.setText(String.format("%03d", velocidadeCarro));
        tempoContadorLabel.setText(String.format("%03d",tempoContador));
        levelLabel.setText(String.format("%03d",level));
    }

}
