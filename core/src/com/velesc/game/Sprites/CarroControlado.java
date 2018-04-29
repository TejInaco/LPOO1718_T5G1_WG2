package com.velesc.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.particles.values.RectangleSpawnShapeValue;
import com.badlogic.gdx.physics.box2d.*;
import com.velesc.game.VelocidadeEscaldante;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
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

        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(32/ VelocidadeEscaldante.PPM,32/VelocidadeEscaldante.PPM);
        b2body = world.createBody(bdef);

        //create a rectangular shape for the car
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(2,5);


        //set physics atributes
        FixtureDef fdef = new FixtureDef();
        fdef.shape = polygonShape;
        fdef.density = 10;
        fdef.restitution = 0.5f;
        fdef.friction = 0.5f;

        b2body.createFixture(fdef);

    }

}
