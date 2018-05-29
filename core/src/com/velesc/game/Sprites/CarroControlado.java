package com.velesc.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.velesc.game.Assets;
import com.velesc.game.InputHandler.InputHandlerAndroid;
import com.velesc.game.InputHandler.InputHandlerDesktop;
import com.velesc.game.VelocidadeEscaldante;


public class CarroControlado extends Carro {
    private static int MAP_LEFT_LIMITS = 5;
    private static int MAP_RIGHT_LIMITS = 530;
    VelocidadeEscaldante game;
    public SpriteBatch batch;
    public Sprite sprite;

    Assets assets;

    private Fisica fisica;

    private boolean flagBoundariesRight = false;
    private boolean flagBoundariesLeft = false;


    public CarroControlado(VelocidadeEscaldante game,World world, int boundsX, int boundsY, int sprite_largura, int sprite_altura){
        super(world, boundsX, boundsY, sprite_largura, sprite_altura);
        this.game = game;
        batch = new SpriteBatch();
        sprite = new Sprite();
        assets = new Assets();
        fisica = new Fisica();
    }
    public int carroPositionY(){
        return (int) this.getBodyCarroControlado().getPosition().y;
    }
    public int carroPositionX(){
        return (int) this.getBodyCarroControlado().getPosition().x;
    }
    public Fisica getFisica(){
        return fisica;
    }
    public Body getBodyCarroControlado(){
        return body;
    }
    public float getLinearVelocity(){
        return this.body.getLinearVelocity().y;
    }
    public Vector2 getPosition(){
        return this.body.getPosition();
    }
    /*
    * returns car's velocity vector relative to the car
    */
    public Vector2 getLocalVelocity() {
        return this.body.getLocalVector(this.body.getLinearVelocityFromLocalPoint(new Vector2(0, 0)));
    }
    /**
     * Calculates the car velocity
     * @returns The car velocity em Kmh
     * */
    public float getSpeedKMH(){
        Vector2 velocity=this.body.getLinearVelocity();
        float len = velocity.len();
        return (len/1000)*3600;
    }
    /**
     * Maintains Linear Velocity of the car only on the Y axis
     * Velocity in X axis is reset to zero
     * */
    public void setLinearVelocity(){
        this.getBodyCarroControlado().setLinearVelocity(0,this.getBodyCarroControlado().getLinearVelocity().y);
    }

    /**
     * Flag to check the left map limit
     * @return true if car position goes beyond the left limit
     * */
    public boolean getFlagBoundariesLeft(){
        return flagBoundariesLeft;
    }
    /**
     * Flag to check the right map limit
     * @return true if car position goes beyond the right limit
     * */

    public boolean getFlagBoundariesRight(){
        return flagBoundariesRight;
    }
    /**
     * Checks for Left, Right and finishing map limits
     * */
    public void checkBoundaries(){
        boundariesLeft();
        boundariesRight();
    }
    /**
     * Checks if the car is beyond the left limits
     * */
    public void boundariesLeft(){
        if(this.getPosition().x < MAP_LEFT_LIMITS){
            this.setLinearVelocity();
            this.flagBoundariesLeft = true;
        }else{
            this.flagBoundariesLeft = false;
        }
    }
    /**
     * Checks if the car is beyond the right limits
     * */
    public void boundariesRight(){
        if(this.getBodyCarroControlado().getPosition().x > MAP_RIGHT_LIMITS){
            this.setLinearVelocity();
            this.flagBoundariesRight = true;
        }else{
            this.flagBoundariesRight = false;
        }
    }
    /**
     * Function to separate from the input from desktop and the input from android
     * @param delta recives a float delta time
     * */
    public void handleInput(float delta){
        if(this.game.getIsMobile() == true){
            InputHandlerAndroid inputandroid = new InputHandlerAndroid();
            inputandroid.updateInputAndroid(this);
        }else{
            InputHandlerDesktop inputdesktop = new InputHandlerDesktop();
            inputdesktop.updateInputDesktop(this);
        }

    }
    public void update(float delta){
        world.step(Gdx.graphics.getDeltaTime(),2,2);
        checkBoundaries();
        this.handleInput(delta);
        sprite.setPosition(body.getPosition().x, body.getPosition().y);
        batch.begin();
        batch.draw(assets.CAMARO,this.getPosition().x, this.getPosition().y,50 + assets.CAMARO.getWidth()/2,25 + assets.CAMARO.getHeight()/2);
        batch.end();
    }

}
