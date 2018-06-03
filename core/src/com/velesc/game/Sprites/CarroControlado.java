package com.velesc.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.velesc.game.Assets;
import com.velesc.game.InputHandler.InputHandlerAndroid;
import com.velesc.game.InputHandler.InputHandlerDesktop;
import com.velesc.game.Screens.EcraJogo;
import com.velesc.game.VelocidadeEscaldante;

public class CarroControlado extends Sprite {
    public enum State { STOP, DRIVING,  FINISHING_LINE }
    public State currentState;
    //public State previousState;
    private static int MAP_LEFT_LIMITS = 5;
    private static int MAP_RIGHT_LIMITS = 530;
    private int initialPositionX = 50;
    private int initialPositionY = 50;

    public World world;
    public Body body;
    private EcraJogo screen;

    public SpriteBatch batch;

    Assets assets;

    private Fisica fisica;

    private boolean flagBoundariesRight = false;
    private boolean flagBoundariesLeft = false;


    public CarroControlado(EcraJogo screen){
        this.screen = screen;
        this.world = screen.getWorld();
        batch = new SpriteBatch();
        assets = new Assets();
        fisica = new Fisica();

        //Define Car in Box2d
        defineCarro();

        currentState = State.DRIVING;
        setBounds(0,0 ,50/VelocidadeEscaldante.PPM, 50/VelocidadeEscaldante.PPM);

    }
    public void defineCarro(){

        BodyDef bdef = new BodyDef();
        bdef.position.set(initialPositionX/VelocidadeEscaldante.PPM,initialPositionY/VelocidadeEscaldante.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(assets.CAMARO.getWidth()/VelocidadeEscaldante.PPM, assets.CAMARO.getHeight()/VelocidadeEscaldante.PPM);
        fdef.shape = shape;
        fdef.density = fisica.DENSITY;
        fdef.restitution = Fisica.RESTITUTION;
        fdef.friction = fisica.FRICTION;
        body.createFixture(fdef).setUserData(this);

    }
    public int carroPositionY() {
        return (int) this.getBodyCarroControlado().getPosition().y;
    }
    public int carroPositionX(){
        return (int) ( this.getBodyCarroControlado().getPosition().x/VelocidadeEscaldante.PPM);
    }
    /**
     * GETS
     * */
    public Fisica getFisica() {
        return fisica;
    }
    public Body getBodyCarroControlado() {
        return body;
    }
    public float getLinearVelocity(){
        return this.body.getLinearVelocity().y;
    }
    public Vector2 getPosition(){
        return this.body.getPosition();
    }
    public State getCurrentState(){
        return currentState;
    }
    /**
     * SETS
     * */
    public void setVelocityToZero(){
        if(getSpeedKMH() <= 0){
            this.currentState = State.STOP;
            this.getBodyCarroControlado().setLinearVelocity(0,0);
        }
    }

    /**
    * @return Vector2 car's velocity vector relative to the car
    */
    public Vector2 getLocalVelocity() {
        return this.body.getLocalVector(this.body.getLinearVelocityFromLocalPoint(new Vector2(0, 0)));
    }
    /**
     * Calculates the car velocity
     * @returns The car velocity em Kmh
     * */
    public float getSpeedKMH(){
        Vector2 velocity= this.body.getLinearVelocity();
        return (velocity.y/100)*3600;
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
        setVelocityToZero();
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
    public void handleInput(float delta) {
        if (screen.getGame().getIsMobile() == true) {
            InputHandlerAndroid inputandroid = new InputHandlerAndroid();
            inputandroid.updateInputAndroid(this);
        } else {
            InputHandlerDesktop inputdesktop = new InputHandlerDesktop();
            inputdesktop.updateInputDesktop(this);
        }
    }


    public void update(float delta){
        world.step(Gdx.graphics.getDeltaTime(),2,2);
        checkBoundaries();
        this.handleInput(delta);
        if(getPosition().y > 80 ){
            this.currentState = State.FINISHING_LINE;
        }
        batch.begin();
        batch.draw(assets.CAMARO,this.getPosition().x, this.getPosition().y,
                50 + assets.CAMARO.getWidth()/2,25 + assets.CAMARO.getHeight()/2);
        batch.end();
    }

}
