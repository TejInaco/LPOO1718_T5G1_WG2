package com.velesc.game.Tools;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.*;

public class B2WorldCreator   {
    public B2WorldCreator(World world, TiledMap map){

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;


        //Esta a definir o objecto RoadLimits onde se houver colisao existe um crash
        /*
        for(MapObject object : map.getLayers().get(0).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(  (rect.getX() +rect.getWidth()/2)/ VelocidadeEscaldante.PPM, (rect.getY() + rect.getHeight() /2)/VelocidadeEscaldante.PPM);
            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
    */
        //MapLayer layer = map.getLayers().get("RoadLimits");

/*
    //    create pipe bodies/fixtures TODO serao os nossos blocos e carros parados || Only needs to change
    //      de index of it
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set( (rect.getX() +rect.getWidth()) /VelocidadeEscaldante.PPM, (rect.getY() + rect.getHeight())/VelocidadeEscaldante.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/10,rect.getHeight()/10);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        //For the briks TODO change the index, mostly liked we will not needed it
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Bloco(world, map, rect);
        }
        FOr the coin bodies TODO check if we will need it
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new CarroSecundario(world, map, rect);
        }*/
    }
}
