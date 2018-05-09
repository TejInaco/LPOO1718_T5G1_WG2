package com.velesc.game.Scenes;

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

public class EcraVizualizado  implements Disposable{

    //Scene2d.ui Stage and its own viewport for HUD
    public Stage stage;
    private Viewport viewport;

    //Time and score for the car Tracking variables
    private Integer worldTimer;
    private float timecount;
    private Integer score;

    //Scene 2d widgets
    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label carLabel;

    public EcraVizualizado(SpriteBatch sb) {

        worldTimer = 300;
        timecount = 0;
        score = 0;

        viewport = new FitViewport(VelocidadeEscaldante.altura,VelocidadeEscaldante.largura, new OrthographicCamera());

        stage = new Stage(viewport, sb);
        /**table to organized labels in certain positions*/
        Table table = new Table();
        /**Alinhamento*/
        table.top();
        table.setFillParent(true);

        /**Representativa do nosso Integer*/
        /**%03d how many numbers will show */
        countdownLabel = new Label(String.format("%03d",worldTimer),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d",worldTimer),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("Time",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("1",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("World",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        carLabel = new Label("VeloEs",new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(carLabel).expandX().padTop(10);
        //TODO Pensar em mudar par
        //expandX pos as labels todas no angulo do x. ve a quantidade de labels e dividias pela distancia
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();

        stage.addActor(table);



    }


    @Override
    public void dispose() {
        stage.dispose();

    }
}
