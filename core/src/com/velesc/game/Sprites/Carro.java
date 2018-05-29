package com.velesc.game.Sprites;

import com.badlogic.gdx.physics.box2d.*;
import com.velesc.game.VelocidadeEscaldante;

public abstract class Carro {

    protected World world;
    protected Body body;

    private  float posINI_X;
    private  float posINI_Y;

    private float sprite_largura;
    private float sprite_altura;

    public Carro(World world, float boundsX, float boundsY, float sp_larg, float sp_alt){
        this.world = world;
        this.posINI_X = boundsX;
        this.posINI_Y = boundsY;
        this.sprite_altura = sp_alt;
        this.sprite_largura = sp_larg;

        body = world.createBody(setBodyDefinitions (
                getCalculationPOSITIONonScreen(posINI_X,sprite_largura),
                getCalculationPOSITIONonScreen(posINI_Y,sprite_altura)
        ));

        body.createFixture(setFixtureDef());
    }
    /**
     * Body definitions
     * @param xx Initial position in xx
     * @param yy Initial position in yy
     * */
    public BodyDef setBodyDefinitions(float xx, float yy){
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(xx,yy);
        return bdef;
    }
    /**
     * Fixture Global definitions
     * */
    public FixtureDef setFixtureDef(){
        FixtureDef fdef = new FixtureDef();
        fdef.shape = setPolygonShape(posINI_X/2, posINI_Y/2);
        fdef.density = 0.15f;
        fdef.restitution = 0.5f;
        fdef.friction = 0.5f;
        return fdef;
    }
    /**
     * Polygon Shape Global definitions
     * */
    public PolygonShape setPolygonShape(float boundsXX, float boundsYY){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(boundsXX, boundsYY);
        return shape;
    }
    /**
     * Calculates the position on the screen
     * */
    public float getCalculationPOSITIONonScreen(float valueXX, float spriteSizeXX ){
        return (valueXX + spriteSizeXX/2) / VelocidadeEscaldante.PPM;
    }

}
