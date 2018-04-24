package com.velesc.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.velesc.game.Sprites.Bloco;
import com.velesc.game.Sprites.CarroSecundario;
import com.velesc.game.VelocidadeEscaldante;

public class B2WorldCreator   {
    public B2WorldCreator(World world, TiledMap map){
        //TODO to move later on to the logic classes of with object
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        //TODO map.getLayers().get(2) the index started from the bottow check pat7 5:53
        /** Ground */
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(  (rect.getX() +rect.getWidth()/2)/ VelocidadeEscaldante.PPM, (rect.getY() + rect.getHeight() /2)/VelocidadeEscaldante.PPM);
            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        /**create pipe bodies/fixtures TODO serao os nossos blocos e carros parados || Only needs to change
         * de index of it*/
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set( (rect.getX() +rect.getWidth()/2) /VelocidadeEscaldante.PPM, (rect.getY() + rect.getHeight() /2)/VelocidadeEscaldante.PPM);
            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        /**For the briks TODO change the index, mostly liked we will not needed it*/
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Bloco(world, map, rect);
        }
        /**FOr the coin bodies TODO check if we will need it*/
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new CarroSecundario(world, map, rect);
        }
    }
}
