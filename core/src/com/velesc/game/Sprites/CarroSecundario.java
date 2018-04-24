package com.velesc.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.velesc.game.VelocidadeEscaldante;

public class CarroSecundario extends InteractiveTileObject {
            //TODO COIN CLASS
    public CarroSecundario(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);

    }
}
