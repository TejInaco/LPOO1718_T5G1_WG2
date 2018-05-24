package com.velesc.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.velesc.game.VelocidadeEscaldante;

public class CarroSecundario extends InteractiveTileObject {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 60;
    Fisica fisica;
    private Array<CarroSecundario> limitsAreas;
    private Vector2 position = new Vector2();
    private Vector2 velocity = new Vector2();
    private Rectangle bounds;
    private Boolean decelerating;

    public CarroSecundario(World world, int posX, int posY,int largura,int altura){
        super(world, posX, posY, largura, altura);
        fisica = new Fisica();
    }
//    public CarroSecundario(Vector2 position) {
//        this.position = position;
//        this.velocity.set(0, 0);
//        this.bounds = new Rectangle(position.x, position.y, WIDTH, HEIGHT);
//        this.decelerating = false;
//    }

    public void update(float deltaTime) {
        if (!decelerating) {
            if (velocity.y < fisica.MAXVELOCITY.y) {
                velocity.add(fisica.ACCELERATION.x * deltaTime, fisica.ACCELERATION.y * deltaTime);
            }
        }
        if (decelerating) {
            if (velocity.y > fisica.MINVELOCITY.y) {
                velocity.add(fisica.DECELERATION.x * deltaTime, fisica.DECELERATION.y * deltaTime);
            }
        }
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        bounds.set(position.x, position.y, WIDTH, HEIGHT);
        decelerating = false;
    }
    public void stopCar() {
        velocity.set(0, 0);
    }

    public void contactGrass() {
        decelerating = true;
    }

    public void contactFinishLine() {
        decelerating = true;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public String getKhm() {
        int khm = (int)(velocity.y/2);
        String formattedKhm = String.format("%03d", khm);
        return formattedKhm;
    }
}
