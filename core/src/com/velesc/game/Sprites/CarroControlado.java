package com.velesc.game.Sprites;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
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

public class CarroControlado {

    private OrthographicCamera gameCamera;

    public SpriteBatch batch;
    public Sprite sprite;

    public World world;
    public Body b2body;

    Assets assets = new Assets();

    public CarroControlado(World world){
        this.world = world;
        create();
    }
    public float carroPositionY(){
        return b2body.getPosition().y;
    }

    public void create(){
        batch = new SpriteBatch();
        sprite = new Sprite();

        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(50,50); //Posicao inicial

        //Create a physics world. The vector is passed in is gravity
        //world = new World(new Vector2(0,-98f), true);
        b2body = world.createBody(bdef);

        //create a rectangular shape for the car
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(2,6);

        //set physics atributes
        FixtureDef fdef = new FixtureDef();
        fdef.shape = polygonShape;

        //TODO Density 1f ?
        fdef.density = 10f;
        fdef.restitution = 0.5f;
        fdef.friction = 0.5f;

        b2body.createFixture(fdef);

    }


    public void update(float delta){
        world.step(Gdx.graphics.getDeltaTime(),6,2);
        sprite.setPosition(b2body.getPosition().x, b2body.getPosition().y);
        batch.begin();
        batch.draw(assets.CAMARO,b2body.getPosition().x,b2body.getPosition().y,50 + assets.CAMARO.getWidth()/2,25 + assets.CAMARO.getHeight()/2);
        batch.end();


    }

}
