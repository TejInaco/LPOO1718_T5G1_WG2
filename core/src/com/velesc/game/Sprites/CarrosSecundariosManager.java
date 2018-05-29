package com.velesc.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.velesc.game.Assets;
/**
 * Class responsible for input enemies in the road
 * */
public class CarrosSecundariosManager{
    private static int  NUMBER_CARROS = 4;

    private static int CAR_0_POSITION_X = 10;
    private static int CAR_1_POSITION_X = 225;
    private static int CAR_2_POSITION_X = 400;
    private static int CAR_3_POSITION_X = 530;

    private int CAR_0_POSITION_Y;
    private int CAR_1_POSITION_Y;
    private int CAR_2_POSITION_Y;
    private int CAR_3_POSITION_Y;

    private static Texture CAR_0_text;
    private static Texture CAR_1_text;
    private static Texture CAR_2_text;
    private static Texture CAR_3_text;

    private Vector2 referencialPosition;

    Assets assets;
    World world;

    SpriteBatch batch;
    Sprite sprite;

    Fisica fisica;

    Vector2 posicao;

    Enemies carroSecundario_0;
//    Enemies carroSecundario_1;
//    Enemies carroSecundario_2;
//    Enemies carroSecundario_3;

    public CarrosSecundariosManager(World world) {
        this.world = world;
        batch =  new SpriteBatch();
        sprite = new Sprite();
        posicao = new Vector2();
        assets = new Assets();
        referencialPosition = new Vector2();
        fisica = new Fisica();

        CAR_0_text = assets.CAR_0;

        posicao = new Vector2(CAR_0_POSITION_X, 50);
        carroSecundario_0 = new Enemies(world,posicao, assets.CAR_0);
        carroSecundario_0.setLinearVelocity(fisica.MEDIUM_DRIVER);
        //        CAR_1_text = assets.CAR_1;
//        CAR_2_text = assets.CAR_2;
//        CAR_3_text = assets.CAR_3;




    }
    public void setReferencialPosition(Body bodyPlayer){
        //fazer o calculo da posicao
        this.referencialPosition.y = bodyPlayer.getPosition().y + 200;
    }
    public void carsDistribution(){

//        carroSecundario_1 = new Enemies(world,CAR_1_POSITION_X);
//        carroSecundario_2 = new Enemies(world,CAR_2_POSITION_X);
//        carroSecundario_3 = new Enemies(world,CAR_3_POSITION_X);;

    }

    public void update(){
//        //Por a posicao do jogador no y referencial
//        this.setReferencialPosition(playerPos);
////        world.step(Gdx.graphics.getDeltaTime(),2,2);
//        sprite.setPosition(carroSecundario_0.getPosition().x,referencialPosition.y);
//        batch.begin();
//        batch.draw(assets.CAR_0,carroSecundario_0.getPosition().x, referencialPosition.y,50 + assets.CAR_0.getWidth()/2,25 + assets.CAR_0.getHeight()/2);
////        batch.draw(assets.CAR_1,this.getPosition().x, this.getPosition().y,50 + assets.CAR_1.getWidth()/2,25 + assets.CAMARO.getHeight()/2);
////        batch.draw(assets.CAR_2,this.getPosition().x, this.getPosition().y,50 + assets.CAR_2.getWidth()/2,25 + assets.CAMARO.getHeight()/2);
////        batch.draw(assets.CAR_3,this.getPosition().x, this.getPosition().y,50 + assets.CAR_3.getWidth()/2,25 + assets.CAMARO.getHeight()/2);
//        batch.end();
//
    }
}
