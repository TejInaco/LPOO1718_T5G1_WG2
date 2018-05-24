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
import com.velesc.game.Assets;
import com.velesc.game.VelocidadeEscaldante;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class CarroControlado extends InteractiveTileObject{

    private OrthographicCamera gameCamera;
    public SpriteBatch batch;
    public Sprite sprite;
    Assets assets;
    Vector2 position;
    private Fisica fisica;
    private boolean flagBoundariesRight = false;
    private boolean flagBoundariesLeft = false;


    public CarroControlado(World world, int boundsX, int boundsY, int sprite_largura, int sprite_altura){
        super(world, boundsX, boundsY, sprite_largura, sprite_altura);
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

    public float getSpeedKMH(){
        Vector2 velocity=this.body.getLinearVelocity();
        float len = velocity.len();
        return (len/1000)*3600;
    }
    public void setLinearVelocity(){
        this.getBodyCarroControlado().setLinearVelocity(0,this.getBodyCarroControlado().getLinearVelocity().y);
    }

    public boolean getFlagBoundariesLeft(){
        return flagBoundariesLeft;
    }
    public boolean getFlagBoundariesRight(){
        return flagBoundariesRight;
    }
    public void checkBoundaries(){
        boundariesLeft();
        boundariesRight();
    }
    public void boundariesLeft(){
        if(this.getPosition().x < 5){
            this.setLinearVelocity();
            this.flagBoundariesLeft = true;
        }else{
            this.flagBoundariesLeft = false;
        }
    }
    public void boundariesRight(){
        if(this.getBodyCarroControlado().getPosition().x > 530){
            this.setLinearVelocity();
            this.flagBoundariesRight = true;
        }else{
            this.flagBoundariesRight = false;
        }
    }

    public void update(){
        world.step(Gdx.graphics.getDeltaTime(),2,2);
        checkBoundaries();
        sprite.setPosition(body.getPosition().x, body.getPosition().y);
        batch.begin();
        batch.draw(assets.CAMARO,this.getPosition().x, this.getPosition().y,50 + assets.CAMARO.getWidth()/2,25 + assets.CAMARO.getHeight()/2);
        batch.end();
    }

}
