package com.velesc.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.velesc.game.Assets;
import com.velesc.game.Screens.EcraJogo;
import com.velesc.game.VelocidadeEscaldante;

import javax.print.attribute.standard.MediaSize;

public class OtherCars extends Enemy{

    private Assets assets;

    private float increment;
    private final float incrementConstant;

    private float initialPositionX;
    private float initialPositionY;

    Fisica fisica;
    private Texture texture;

    public SpriteBatch batch;

    /**
     * Construct
     * */
    public OtherCars(EcraJogo screen, float x, float y, float velConstante, Texture texture) {
        super(screen, x, y);
        batch = new SpriteBatch();
        this.texture = texture;
        incrementConstant = velConstante;
        fisica = new Fisica();

        initialPositionX = x;
        initialPositionY = y;

        setBounds(getX(), getY(), texture.getWidth() / VelocidadeEscaldante.PPM, texture.getHeight() / VelocidadeEscaldante.PPM);
        increment = 0;
        assets = new Assets();
    }

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(10/ VelocidadeEscaldante.PPM,15/VelocidadeEscaldante.PPM);
        fdef.shape = shape;
        fdef.density = fisica.DENSITY;
        fdef.restitution = fisica.RESTITUTION;
        fdef.friction = fisica.FRICTION;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
        b2body.setActive(true);

    }
    /**
     * @return body position in Vector 2 Format
     * */
    public Vector2 getPosition(){
        return this.b2body.getPosition();
    }
    /**
     *Draws the texture in the body position
     * */
    public void draw(){
            batch.begin();
            batch.draw(texture, initialPositionX, initialPositionY+increment,50 + texture.getWidth()/2,25 + texture.getHeight()/2);
            setPosition(initialPositionX,initialPositionY+increment);
            batch.end();
    }
    public void update(float dt){
        increment += incrementConstant;
        this.draw();
    }

    @Override
    public boolean hitByEnemy(float posPlayerX, float posPlayerY, float largura, float altura) {

        if(posPlayerX > this.getPosition().x  && posPlayerX  < this.getPosition().x + largura){
            if(posPlayerY > this.getPosition().y && posPlayerY  < this.getPosition().y+ altura)
                return true;
        }
        return false;
    }

}
