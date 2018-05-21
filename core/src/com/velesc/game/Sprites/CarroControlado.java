package com.velesc.game.Sprites;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.values.RectangleSpawnShapeValue;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sun.corba.se.impl.orb.ORBVersionImpl;
import com.velesc.game.Assets;
import com.velesc.game.VelocidadeEscaldante;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class CarroControlado extends ApplicationAdapter{
    public SpriteBatch batch;
    public Sprite sprite;
    public World world;
    public Body b2body;


    Assets assets = new Assets();

    public CarroControlado(World world){
        this.world = world;
        create();
    }
    @Override
    public void create(){
        batch = new SpriteBatch();
        sprite = new Sprite(assets.CAMARO);

        //Center the sprite in the top/middle of the screen
        sprite.setPosition(32/ VelocidadeEscaldante.PPM,
                32/VelocidadeEscaldante.PPM);


        BodyDef bdef = new BodyDef();
        //Posicao inicial

        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(sprite.getX(),sprite.getY());
        //Create a physics world. The vector is passed in is gravity
        //world = new World(new Vector2(0,-98f), true);
        b2body = world.createBody(bdef);

        //create a rectangular shape for the car
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(1,3);


        //set physics atributes
        FixtureDef fdef = new FixtureDef();
        fdef.shape = polygonShape;
        //TODO Density 1f ?
        fdef.density = 10f;
        fdef.restitution = 0.5f;
        fdef.friction = 0.5f;

        b2body.createFixture(fdef);

    }
    @Override
    public void render(){
        world.step(Gdx.graphics.getDeltaTime(),6,2);
        sprite.setPosition(b2body.getPosition().x, b2body.getPosition().y);

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(sprite, sprite.getX(),sprite.getY());
        batch.end();

    }
    @Override
    public void dispose(){
        world.dispose();
    }

}
