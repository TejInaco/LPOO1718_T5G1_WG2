package com.velesc.game.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
public class EcraVizualizado  implements Disposable{

    //Scene2d.ui Stage and its own viewport for HUD
    public Stage stage;
    private Viewport viewport;

    //Time and score for the car Tracking variables
    private float timeCount;
    private Integer tempoContador;
    private Integer pontos;
    private Integer velocidadeCarro;

    //Scene 2d widgets
    Label tempoContadorLabel;
    Label pontosLabel;
    Label velocidadeCarroLabel;
    Label tempoContador_NameLabel;
    Label pontos_NameLabel;
    Label velocidadeCarro_NameLabel;

    public EcraVizualizado(){}

    public EcraVizualizado(SpriteBatch sb) {
        tempoContador = 0;
        pontos = 0;
        velocidadeCarro = 0;

        viewport = new FitViewport(VelocidadeEscaldante.largura,VelocidadeEscaldante.altura, new OrthographicCamera());

        stage = new Stage(viewport, sb);
        /**table to organized labels in certain positions*/
        Table table = new Table();
        /**Alinhamento*/
        table.top();
        table.setFillParent(true);

        /**Representativa do nosso Integer*/
        /**%03d how many numbers will show */
        tempoContadorLabel = new Label(String.format("%03d",tempoContador),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        pontosLabel = new Label(String.format("%06d",pontos),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        velocidadeCarroLabel = new Label(String.format("%03d", velocidadeCarro),new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        tempoContador_NameLabel = new Label("T E M P O",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        pontos_NameLabel = new Label("P O N T O S",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        velocidadeCarro_NameLabel = new Label("Velocidade",new Label.LabelStyle(new BitmapFont(), Color.WHITE));

    //Primeira Linha de info do topo
        table.add(tempoContador_NameLabel).expandX().padTop(10);
        table.add(pontos_NameLabel).expandX().padTop(10);
        table.add(velocidadeCarro_NameLabel).expandX().padTop(10);
    //Segunda Linha de info do topo
        table.row();
        table.add(tempoContadorLabel).expandX();
        table.add(pontosLabel).expandX();
        table.add(velocidadeCarroLabel).expandX();

        stage.addActor(table);
    }

    /**
     * Sets and Gets
     * */
    public void setTimeCount(){
        timeCount = 0;
    }
    public void setTempoContador(int value){
        tempoContador = value;
    }
    public void setPontos(int value){
        pontos = value;
    }
    public void setVelocidadeCarro(int value){
        velocidadeCarro = value;
    }
    public float getTimeCount(){
        return timeCount;
    }
    public int getTempoContador(){
        return tempoContador;
    }
    public int getPontos(){
        return pontos;
    }
    public int getVelocidadeCarro(){
        return velocidadeCarro;
    }
    public void setWorldTimer(Integer value){
        this.tempoContador  = value;
    }
    public void update(float dt){
        timeCount += dt;
        if(timeCount >= 1){
            tempoContador++;
            tempoContadorLabel.setText(String.format("%03d",tempoContador));
            timeCount = 0;

        }
    }
    public void setPontos_NameLabel(int pontos){
        pontosLabel.setText(String.format("%06d",pontos));
    }

    public void addPontos(int value){
        //pontos += value;
        pontos = value;                        //for debuging
        setPontos_NameLabel(pontos);
    }

    public void addVelocidade (int value){
        velocidadeCarro = value;
        velocidadeCarroLabel.setText(String.format("%03d", velocidadeCarro));
    }

    @Override
    public void dispose() {
        stage.dispose();

    }
}
