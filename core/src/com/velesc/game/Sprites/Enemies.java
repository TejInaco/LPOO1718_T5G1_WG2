package com.velesc.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Enemies extends Carro {


    public static final int WIDTH = 30;
    public static final int HEIGHT = 60;

    private Vector2 position = new Vector2();
    private Vector2 velocity = new Vector2();
    private Boolean decelerating;

    public Enemies(World world, Vector2 positionCar, Texture text){
        super(world, positionCar.x, positionCar.y, text.getWidth(), text.getHeight());

    }
    public void setLinearVelocity(Vector2 vec){
        this.body.setLinearVelocity(vec);
    }
    public float getLinearVelocity(){
        return this.body.getLinearVelocity().y;
    }


    public void update(){

    }


//    public void update(float deltaTime) {
//        if (!decelerating) {
//            if (velocity.y < fisica.MAXVELOCITY.y) {
//                velocity.add(fisica.ACCELERATION.x * deltaTime, fisica.ACCELERATION.y * deltaTime);
//            }
//        }
//        if (decelerating) {
//            if (velocity.y > fisica.MINVELOCITY.y) {
//                velocity.add(fisica.DECELERATION.x * deltaTime, fisica.DECELERATION.y * deltaTime);
//            }
//        }
//        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
//        bounds.set(position.x, position.y, WIDTH, HEIGHT);
//        decelerating = false;
//    }
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

    public String getKhm() {
        int khm = (int)(velocity.y/2);
        String formattedKhm = String.format("%03d", khm);
        return formattedKhm;
    }

}
