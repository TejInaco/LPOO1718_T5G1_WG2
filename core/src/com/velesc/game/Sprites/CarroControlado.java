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


    public CarroControlado(World world, int boundsX, int boundsY, int sprite_largura, int sprite_altura){
        super(world, boundsX, boundsY, sprite_largura, sprite_altura);
        batch = new SpriteBatch();
        sprite = new Sprite();
        assets = new Assets();

    }
//For debuging  - 1
    public int carroPositionY(){
        return (int) this.getBodyCarroControlado().getPosition().y;
    }
    public int carroPositionX(){
        return (int) this.getBodyCarroControlado().getPosition().x;
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

//For debuging -1 -end

    public void update(){
        world.step(Gdx.graphics.getDeltaTime(),2,2);
        sprite.setPosition(body.getPosition().x, body.getPosition().y);
        batch.begin();
        batch.draw(assets.CAMARO,body.getPosition().x,body.getPosition().y,50 + assets.CAMARO.getWidth()/2,25 + assets.CAMARO.getHeight()/2);
        batch.end();
    }

}
