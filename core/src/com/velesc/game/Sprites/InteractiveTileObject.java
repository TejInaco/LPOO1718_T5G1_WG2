package com.velesc.game.Sprites;

import com.badlogic.gdx.physics.box2d.*;
import com.velesc.game.VelocidadeEscaldante;

public abstract class InteractiveTileObject {

    protected World world;
    protected Body body;



    private  float posINI_X;
    private  float posINI_Y;

    private  float sprite_largura;
    private  float sprite_altura;

    public InteractiveTileObject(World world, float boundsX, float boundsY, float sprite_largura, float sprite_altura){
        this.world = world;
        this.posINI_X = boundsX;
        this.posINI_Y = boundsY;
        this.sprite_altura = sprite_altura;
        this.sprite_largura = sprite_largura;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.DynamicBody;
//        bdef.position.set(positionX,positionY);
        bdef.position.set(  (posINI_X + sprite_largura/2)/ VelocidadeEscaldante.PPM, (boundsY + sprite_altura /2)/VelocidadeEscaldante.PPM);
        body = world.createBody(bdef);

        shape.setAsBox(boundsX/2, boundsY/2);
        fdef.shape = shape;
        fdef.density = 0.15f;
        fdef.restitution = 0.5f;
        fdef.friction = 0.5f;

        body.createFixture(fdef);

    }
}
