package com.velesc.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.velesc.game.Assets;
import com.velesc.game.VelocidadeEscaldante;

public abstract class InteractiveTileObject {

    protected World world;

    // protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;



    private  int posINI_X;
    private  int posINI_Y;

    private  int sprite_largura;
    private  int sprite_altura;

    public InteractiveTileObject(World world, int boundsX, int boundsY, int sprite_largura, int sprite_altura){
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
