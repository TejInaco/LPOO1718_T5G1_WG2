package com.velesc.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.velesc.game.VelocidadeEscaldante;

import java.awt.geom.RectangularShape;

public class CarroControlado extends Sprite{
    public World world;
    public Body b2body;

    public CarroControlado(World world){
        this.world = world;
        defineCarroControlado();
    }
    public void defineCarroControlado(){
        BodyDef bdef = new BodyDef();
        //Posicao inicial
        bdef.position.set(32/ VelocidadeEscaldante.PPM,32/VelocidadeEscaldante.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        //TODO COMO È O NOSSO CARRO DEVE SER UM RECTAnGULO
        CircleShape shape = new CircleShape();
        //TODO RectangularShape shape1 = new Rectangular shape
        shape.setRadius(5/VelocidadeEscaldante.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);

    }

}
